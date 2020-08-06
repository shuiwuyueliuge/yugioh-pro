package cn.mayu.yugioh.cardsource.core.ourocg;

import com.google.common.hash.HashCode;
import com.sun.org.apache.xpath.internal.operations.Equals;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class OurocgQueueGuardian {

    private static final Queue<PriorityQueueModel> OUROCG_QUEUE;

    private static final Queue<PriorityQueueModel> REPEAT_QUEUE;

    static {
        OUROCG_QUEUE = new LinkedBlockingQueue<>();
        REPEAT_QUEUE = new LinkedBlockingQueue<>();
    }

    public static PriorityQueueModel take() {
        return OUROCG_QUEUE.poll();
    }

    public static PriorityQueueModel takeRepeat() {
        return REPEAT_QUEUE.poll();
    }

    public static void addOne(String content, DataTypeEnum dataType, String channelId, String subscribe) {
        PriorityQueueModel queueModel = new PriorityQueueModel(content, dataType, channelId, subscribe);
        OUROCG_QUEUE.add(queueModel);
    }

    public static void addAll(List<String> content, DataTypeEnum dataType, String channelId, String subscribe) {
        content.stream().forEach(data -> {
            PriorityQueueModel queueModel = new PriorityQueueModel(data, dataType, channelId, subscribe);
            synchronized ("") {
                if (OUROCG_QUEUE.contains(queueModel)) {
                    REPEAT_QUEUE.add(queueModel);
                    return;
                }

                OUROCG_QUEUE.add(queueModel);
            }
        });
    }

    @Data
    @AllArgsConstructor
    static class PriorityQueueModel {

        private String data;

        private DataTypeEnum dataType;

        private String channelId;

        private String subscribe;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            PriorityQueueModel that = (PriorityQueueModel) o;
            return Objects.equals(data, that.data) &&
                    dataType == that.dataType &&
                    Objects.equals(subscribe, that.subscribe);
        }

        @Override
        public int hashCode() {
            return Objects.hash(data, dataType, subscribe);
        }
    }
}
