package cn.mayu.yugioh.cardsource.core.ourocg;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.slf4j.MDC;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

public class OurocgQueueGuardian {

    private static final BlockingQueue<PriorityQueueModel> OUROCG_QUEUE;

    static {
        OUROCG_QUEUE = new PriorityBlockingQueue<PriorityQueueModel>(1000, (o1, o2) -> { // TODO initialCapacity is 1000，package maybe not so mach
            if (o1.getPriority() > o2.getPriority()) return -1;
            if (o1.getPriority() < o2.getPriority()) return 1;
            return 0;
        });
    }

    public static PriorityQueueModel take() {
        try {
            return OUROCG_QUEUE.take();
        } catch (InterruptedException e) {
            return null;
        }
    }

    public static void addOne(String content, Integer priority, DataTypeEnum dataType) {
        PriorityQueueModel queueModel = new PriorityQueueModel(content, priority, dataType, "");
        synchronized ("") {
            OUROCG_QUEUE.remove(queueModel);
            OUROCG_QUEUE.add(queueModel);
        }
    }

    public static void addAll(List<String> content, Integer priority, DataTypeEnum dataType, String channelId) {
        content.stream().forEach(data -> {
            PriorityQueueModel queueModel = new PriorityQueueModel(data, priority, dataType, channelId);
            synchronized ("") {
                OUROCG_QUEUE.remove(queueModel);
                OUROCG_QUEUE.add(queueModel);
            }
        });
    }

    @Data
    @AllArgsConstructor
     static class PriorityQueueModel {

        private String data;

        private int priority;

        private DataTypeEnum dataType;

        private String channelId;
    }
}

enum DataTypeEnum {

    PACKAGE, LIMIT;
}
