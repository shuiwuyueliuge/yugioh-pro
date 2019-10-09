package cn.mayu.yugioh.reptile.ourocg.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class OurocgCrawling {
	
	private Logger log = LoggerFactory.getLogger(getClass());
	
	private static final String BASE_ULR = "https://www.ourocg.cn/card/list-5/%s";

	@Scheduled(cron = "*/1 * * * * ?")
	public void CardBasicDataCrawing() {
		
	}
}
