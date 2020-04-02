package cn.mayu.yugioh.basic.gateway.route.service;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;
import cn.mayu.yugioh.basic.gateway.route.entity.DictEntity;
import cn.mayu.yugioh.basic.gateway.route.repository.DictRepository;

@Service
public class DictServiceImpl implements DictService {
	
	private DictRepository dictRepository;
	
	private DictCache dictCache;
	
	private static final long RELOAD_TIME = 60L;
	
	@Autowired
	public DictServiceImpl(CacheManager cacheManager, DictRepository dictRepository) {
		this.dictRepository = dictRepository;
		dictCache = new DictCache(cacheManager);
		newThread(this).start();
	}
	
	@Override
	public String getValue(int type, int valueId) {
		return dictCache.getValue(type, valueId);
	}
	
	@Override
	public void run() {
		while(true) {
			dictCache.doCache(dictRepository.findAll());
			try {
				TimeUnit.SECONDS.sleep(RELOAD_TIME);
			} catch (InterruptedException e) {
			}
		}
	}
	
	@Override
	public Thread newThread(Runnable command) {
		Thread thread = new Thread(command);
		thread.setDaemon(true);
		return thread;
	}
	
	class DictCache {
		
		private static final String CACHE_NAME = "dictCache";
		
		private static final String CACHE_KEY_TEMPLATE = "%s:%s";
		
		private Cache cache;
		
		public DictCache(CacheManager cacheManager) {
			cache = cacheManager.getCache(CACHE_NAME);
		}
		
		private void doCache(List<DictEntity> list) {
			list.stream().forEach(this::put);
		}
		
		private void put(DictEntity dict) {
			cache.put(getKey(dict.getType(), dict.getValueId()), dict);
		}
		
		private String getKey(int type, int valueId) {
			return String.format(CACHE_KEY_TEMPLATE, type, valueId);
		}
		
		private String getValue(int type, int valueId) {
			DictEntity dict = cache.get(getKey(type, valueId), DictEntity.class);
			return dict == null ? null : dict.getValue();
		}
	}
}
