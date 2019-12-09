package cn.mayu.yugioh.sync.ourocg.task;

import static cn.mayu.yugioh.common.core.util.FileUtil.genTodayFileName;
import java.io.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import cn.mayu.yugioh.sync.ourocg.service.OurocgDataService;
import cn.mayu.yugioh.sync.ourocg.service.TaskMemoryService;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class OurocgCrawlingTask {

	private static final String BASE_ULR = "https://www.ourocg.cn/card/list-5/%s";

	private static final String LIMIT_LATRST_URL = "https://www.ourocg.cn/Limit-Latest";
	
	private static final String OUROCG_PAGE_KEY = "crawing:ourocg:page";

	@Autowired
	private OurocgDataService ourocgDataService;
	
	@Autowired
	private TaskMemoryService memoryService;

	@Scheduled(cron = "${crawing.corn}")
	public void crawing() {
		// card info
		metaDataCrawing();

		// package info
		try {
			ourocgDataService.packageDetilSave();
		} catch (Exception e) {
			log.error("OurocgCard packageDetilSave error [{}]", e);
		}

		// limit info
		try {
			ourocgDataService.limitInfoSave(LIMIT_LATRST_URL);
		} catch (Exception e) {
			log.error("OurocgCard limitInfo error [{}]", e);
		}
	}

	private void metaDataCrawing() {
		File file = new File(genTodayFileName());
		if (file.exists()) file.delete();
		long num = memoryService.checkMemory(OUROCG_PAGE_KEY);
		if (num == 0L) {
			memoryService.markMemory(OUROCG_PAGE_KEY, 1L);
			num = 1L;
		}
		
		while (true) {
			num = memoryService.checkMemory(OUROCG_PAGE_KEY);
			String url = String.format(BASE_ULR, num);
			try {
				if (!ourocgDataService.ourocgDataInFile(url)) {
					memoryService.remove(OUROCG_PAGE_KEY);
					break;
				}
			} catch (Exception e) {
				log.error("Ourocg Crawling url [{}] error [{}]", url, e);
				continue;
			} finally {
				memoryService.increaseBy(OUROCG_PAGE_KEY);
			}
		}
	}
}
