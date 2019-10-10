package cn.mayu.yugioh.reptile.ourocg.task;

import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import cn.mayu.yugioh.reptile.ourocg.manager.CardMetaDataFindManager;

@Component
public class OurocgCrawlingTask {

	private Logger log = LoggerFactory.getLogger(getClass());

	private static final String BASE_ULR = "https://www.ourocg.cn/card/list-5/%s";

	@Autowired
	private CardMetaDataFindManager dataFindManager;

	@Value("${spring.cloud.config.label}")
	private int lable;

	@Value("${reptileOurocg.count}")
	private int count;

	@Scheduled(cron = "*/1 * * * * ?")
	public void CardBasicDataCrawing() {
		IntStream.rangeClosed(1, 1001).boxed().forEach(num -> exec(String.format(BASE_ULR, num)));
	}
	
	private void exec(String url) {
		try {
			dataFindManager.doExec(url);
		} catch (Exception e) {
			log.error("Ourocg Crawling url [{}] error [{}]", url, e);
		}
	}
}
