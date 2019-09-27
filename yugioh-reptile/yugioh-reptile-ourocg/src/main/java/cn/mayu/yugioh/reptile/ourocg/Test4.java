package cn.mayu.yugioh.reptile.ourocg;

import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Test4 {
	
	Logger log = LoggerFactory.getLogger(getClass());

	public static void main(String[] args) throws Exception {	
		long time = System.currentTimeMillis();
		IntStream.rangeClosed(1, 1001).mapToObj(num -> {
			return num;
		}).collect(Collectors.toList()).parallelStream().forEach(cn.mayu.yugioh.reptile.ourocg.CardMetaDataFinder::exec);
		
		System.out.println(System.currentTimeMillis() - time);
	}
}
