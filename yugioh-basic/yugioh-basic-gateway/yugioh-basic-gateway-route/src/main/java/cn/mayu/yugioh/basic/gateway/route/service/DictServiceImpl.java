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
	
	private static final long RELOAD_MINUTES_TIME = 30L;
	
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
	public Integer getName(int type, String value) {
		return dictCache.getName(type, value);
	}
	
	@Override
	public void run() {
		while(true) {
			dictCache.doCache(dictRepository.findAll());
			try {
				TimeUnit.MINUTES.sleep(RELOAD_MINUTES_TIME);
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
		
		private Integer getName(int type, String value) {
			Integer name = cache.get(getKey(type, value), Integer.class);
			return name == null ? null : name;
		}
		
		private String getValue(int type, int valueId) {
			String value = cache.get(getKey(type, valueId), String.class);
			return value == null ? null : value;
		}

		private void doCache(List<DictEntity> list) {
			list.stream().forEach(this::put);
		}
		
		private void put(DictEntity dict) {
			cache.put(getKey(dict.getType(), dict.getValueId()), dict.getValue());
			cache.put(getKey(dict.getType(), dict.getValue()), dict.getValueId());
		}
		
		private String getKey(int type, Object valueId) {
			return String.format(CACHE_KEY_TEMPLATE, type, valueId);
		}
	}
}
