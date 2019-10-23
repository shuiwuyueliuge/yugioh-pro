package cn.mayu.yugioh.reptile.ourocg.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
import cn.mayu.yugioh.reptile.ourocg.service.OurocgDataService;

//@Component
public class OurocgCrawlingTask {

	private Logger log = LoggerFactory.getLogger(getClass());

	private static final String BASE_ULR = "https://www.ourocg.cn/card/list-5/%s";

	@Autowired
	private OurocgDataService ourocgDataService;

	@Value("${spring.cloud.config.label}")
	private int lable;

	@Value("${reptileOurocg.count}")
	private int count;

	@Scheduled(cron = "*/1 * * * * ?")
	public void CardBasicDataCrawing() {
		int num = 1;
		while (true) {
			if (num % count != lable) {
				num++;
				continue;
			}
			
			String url = String.format(BASE_ULR, num);
			try {
				if (!ourocgDataService.findOurocgData(url)) {
					break;
				}
			} catch (Exception e) {
				log.error("Ourocg Crawling url [{}] error [{}]", url, e);
				continue;
			}
			
			num++;
		}
	}
}
