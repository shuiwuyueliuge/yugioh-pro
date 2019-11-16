package cn.mayu.yugioh.sync.local.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import cn.mayu.yugioh.sync.local.service.CardService;
import cn.mayu.yugioh.sync.local.service.IndexService;

@Component
public class CardSyncTask {
	
	@Autowired
	private IndexService indexservice;
	
	@Autowired
	private CardService cardservice;

	@Scheduled(cron = "*/1 * * * * ?")
	public void sync() {
		indexservice.indexCache();
		cardservice.saveCardData();
	}
}
