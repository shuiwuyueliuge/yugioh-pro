package cn.mayu.org.yugioh.security.core.base.validatecode;

import java.util.concurrent.TimeUnit;
import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.CacheConfiguration;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.config.units.EntryUnit;
import org.ehcache.config.units.MemoryUnit;
import org.ehcache.expiry.Duration;
import org.ehcache.expiry.Expirations;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DefaultValidateCodeManager implements ValidateCodeManager {
	
	private static final String CACHE_NAME = "codeCache";
	
	private Cache<String, String> cache;

	public DefaultValidateCodeManager() {
		this.cache = cacheManager().getCache(CACHE_NAME, String.class, String.class);
		log.debug("初始化默认缓存成功,缓存alias[" + CACHE_NAME + "]");
	}

	@Override
	public boolean save(String key, String code) {
		cache.put(key, code);
		log.debug("默认缓存存入key[" + key + "]code:[" + code + "]");
		return Boolean.TRUE;
	}

	@Override
	public String get(String key) {
		return cache.get(key);
	}
	
	public CacheManager cacheManager() {
		CacheConfiguration<String, String> configuration = CacheConfigurationBuilder
				.newCacheConfigurationBuilder(String.class, String.class,
						ResourcePoolsBuilder.newResourcePoolsBuilder().heap(1000L, EntryUnit.ENTRIES).offheap(100L,MemoryUnit.MB))
				.withExpiry(Expirations.timeToLiveExpiration(Duration.of(30L, TimeUnit.SECONDS)))
				.withExpiry(Expirations.timeToIdleExpiration(Duration.of(60L, TimeUnit.SECONDS))).build();
		return CacheManagerBuilder.newCacheManagerBuilder().withCache(CACHE_NAME, configuration).build(true);
	}
}
