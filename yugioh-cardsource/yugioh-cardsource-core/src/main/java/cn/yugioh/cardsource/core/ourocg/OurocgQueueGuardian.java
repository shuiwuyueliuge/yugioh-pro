package cn.yugioh.cardsource.core.ourocg;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

public class OurocgQueueGuardian {

    private static final BlockingQueue<PriorityQueueModel> OUROCG_QUEUE;

    static {
        OUROCG_QUEUE = new PriorityBlockingQueue<PriorityQueueModel>(1000, (o1, o2) -> { // initialCapacity is 1000， i think package maybe not so mach
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
        PriorityQueueModel queueModel = new PriorityQueueModel(content, priority, dataType);
        synchronized ("") {
            OUROCG_QUEUE.remove(queueModel);
            OUROCG_QUEUE.add(queueModel);
        }
    }

    public static void addAll(List<String> content, Integer priority, DataTypeEnum dataType) {
        content.stream().forEach(data -> {
            PriorityQueueModel queueModel = new PriorityQueueModel(data, priority, dataType);
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
    }
}

enum DataTypeEnum {

    PACKAGE, LIMIT;
}
