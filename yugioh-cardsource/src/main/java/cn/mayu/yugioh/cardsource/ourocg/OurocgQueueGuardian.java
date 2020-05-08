package cn.mayu.yugioh.cardsource.ourocg;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import lombok.AllArgsConstructor;
import lombok.Data;

public class OurocgQueueGuardian {
	
	private static final BlockingQueue<PriorityQueueModel> PACKAGE_URL_QUEUE;
	
	static {
		PACKAGE_URL_QUEUE = new PriorityBlockingQueue<PriorityQueueModel>(1000, (o1, o2) -> {
			if (o1.getStatus() > o2.getStatus()) return -1;
			if (o1.getStatus() < o2.getStatus()) return 1;
			return 0;
		});
	}
	
	public static String take() {
		try {
			return PACKAGE_URL_QUEUE.take().getData();
		} catch (InterruptedException e) {
			return "";
		}
	}

	public static void syncAdd(String content, Integer status) {
		PriorityQueueModel queueModel = new PriorityQueueModel(content, status);
		synchronized ("") {
			PACKAGE_URL_QUEUE.remove(queueModel);
			PACKAGE_URL_QUEUE.add(queueModel);
		}
	}
	
	public static void addAll(List<String> content) {
		content.stream().forEach(data -> {
			PriorityQueueModel queueModel = new PriorityQueueModel(data, 0);
			PACKAGE_URL_QUEUE.add(queueModel);
		});
	}
	
	@Data
	@AllArgsConstructor
	private static class PriorityQueueModel {
		
		private String data;
		
		// 0 非手动添加， 1手动添加，2立即执行
		private int status;
	}
}
