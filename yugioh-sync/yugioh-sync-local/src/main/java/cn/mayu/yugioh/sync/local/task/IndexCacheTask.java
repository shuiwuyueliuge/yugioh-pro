package cn.mayu.yugioh.sync.local.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import cn.mayu.yugioh.sync.local.service.IndexService;

@Component
public class IndexCacheTask {
	
	@Autowired
	private IndexService indexservice;

	@Scheduled(cron = "${index.cache.corn}")
	public void crawing() {
		indexservice.indexCache();
	}
}
