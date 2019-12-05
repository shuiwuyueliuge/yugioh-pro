package cn.mayu.yugioh.sync.ourocg.task;

import static cn.mayu.yugioh.common.core.util.FileUtil.genTodayFileName;
import java.io.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import cn.mayu.yugioh.sync.ourocg.service.OurocgDataService;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class OurocgCrawlingTask {

	private static final String BASE_ULR = "https://www.ourocg.cn/card/list-5/%s";

	private static final String LIMIT_LATRST_URL = "https://www.ourocg.cn/Limit-Latest";

	@Autowired
	private OurocgDataService ourocgDataService;

	@Value("${spring.cloud.config.label}")
	private int lable;

	@Value("${reptileOurocg.count}")
	private int count;

	@Scheduled(cron = "*/1 * * * * ?")
	public void crawing() {
		// limit info
		try {
			ourocgDataService.limitInfoSave(LIMIT_LATRST_URL);
		} catch (Exception e) {
			log.error("OurocgCard limitInfo error [{}]", e);
		}

		// find card data
		metaDataCrawing();
		
		// package info
		try {
			ourocgDataService.packageDetilSave();
		} catch (Exception e) {
			log.error("OurocgCard packageDetilSave error [{}]", e);
		}
	}

	private void metaDataCrawing() {
		File file = new File(genTodayFileName());
		if (file.exists()) file.delete();
		int num = 1;
		while (true) {
			if (num % count != lable) {
				num++;
				continue;
			}

			String url = String.format(BASE_ULR, num);
			try {
				if (!ourocgDataService.ourocgDataInFile(url)) {
					break;
				}
			} catch (Exception e) {
				log.error("Ourocg Crawling url [{}] error [{}]", url, e);
				num++;
				continue;
			}

			num++;
		}
	}
}
