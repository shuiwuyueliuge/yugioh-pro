package cn.mayu.org.yugioh.security.core.base.config;

import java.time.Duration;
import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.CacheConfiguration;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ExpiryPolicyBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.config.units.EntryUnit;
import org.ehcache.config.units.MemoryUnit;
import cn.mayu.org.yugioh.security.core.base.property.EhcacheProperty;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EhcacheConfig {
	
	private static final String CACHE_NAME = "inMemoryCodeCache";
	
	private Cache<String, String> cache;
	
	public EhcacheConfig(EhcacheProperty ehcacheProperty) {
		cache = cacheManager(ehcacheProperty).getCache(CACHE_NAME, String.class, String.class);
		if (log.isDebugEnabled()) {
			log.debug("init validate code cache: [{}]", CACHE_NAME);
		}
	}

	private CacheManager cacheManager(EhcacheProperty ehcacheProperty) {
		ResourcePoolsBuilder builder = ResourcePoolsBuilder.newResourcePoolsBuilder()
		  				    							   .heap(ehcacheProperty.getHeapSize(), EntryUnit.ENTRIES)
		                                                   .offheap(ehcacheProperty.getOffHeapSize(), MemoryUnit.MB);
		CacheConfiguration<String, String> configuration = CacheConfigurationBuilder
				.newCacheConfigurationBuilder(String.class, String.class, builder)
				.withExpiry(ExpiryPolicyBuilder.timeToIdleExpiration(Duration.ofSeconds(ehcacheProperty.getTimeToIdleSecond())))
				.withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(ehcacheProperty.getTimeToLiveSecond())))
				.build();
		return CacheManagerBuilder.newCacheManagerBuilder().withCache(CACHE_NAME, configuration).build(true);
	}
	
	public Cache<String, String> getCache() {
		return this.cache;
	}
}
