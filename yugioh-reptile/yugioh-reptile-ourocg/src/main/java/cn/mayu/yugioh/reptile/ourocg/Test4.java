package cn.mayu.yugioh.reptile.ourocg;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Test4 {
	
	Logger log = LoggerFactory.getLogger(getClass());

	static ExecutorService service = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

	public void test() throws Exception {	
		long time = System.currentTimeMillis();
//		IntStream.rangeClosed(1, 1001).mapToObj(num -> {
//			return num;
//		}).collect(Collectors.toList()).parallelStream().forEach(CardMeatDataFinder::exec);

		for (int j = 1; j <= 1001; j++) {
			service.execute(new B(j));
			while (((ThreadPoolExecutor) service).getActiveCount() > 200) {}
		}

		while (((ThreadPoolExecutor) service).getActiveCount() != 0) {}

		System.out.println((System.currentTimeMillis() - time) / 1000);
	}
}
