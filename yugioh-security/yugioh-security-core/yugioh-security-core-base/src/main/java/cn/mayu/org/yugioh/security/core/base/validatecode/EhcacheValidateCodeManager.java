package cn.mayu.org.yugioh.security.core.base.validatecode;

import org.ehcache.Cache;
import cn.mayu.org.yugioh.security.core.base.config.EhcacheConfig;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EhcacheValidateCodeManager implements ValidateCodeManager {
	
	private Cache<String, String> cache;

	public EhcacheValidateCodeManager(EhcacheConfig ehcacheConfig) {
		this.cache = ehcacheConfig.getCache();
	}
	
	@Override
	public boolean save(ValidateCodeContext codeContext) {
		String key = codeContext.getKey();
		String code = codeContext.getCode();
		if (log.isDebugEnabled()) {
			log.debug("cached key: [{}], code: [{}]", key, code);
		}
		
		cache.put(key, code);
		return Boolean.TRUE;
	}

	@Override
	public synchronized String getAndRemove(String key) {
		String cached = cache.get(key);
		cache.remove(key);
		return cached;
	}
}
