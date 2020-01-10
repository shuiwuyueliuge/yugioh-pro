package cn.mayu.yugioh.basic.authorize.config;

import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class AuthRedisTokenStore extends RedisTokenStore {
	
	private RedisTemplate<String, String> rt;

	public AuthRedisTokenStore(RedisConnectionFactory connectionFactory, RedisTemplate<String, String> rt) {
		super(connectionFactory);
		this.rt = rt;
	}

	@Override
	public void storeAccessToken(OAuth2AccessToken token, OAuth2Authentication authentication) {
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
	    String sessionId = attributes.getRequest().getParameter("sessionId");
	    rt.opsForValue().set(String.format("sessionId:%s", sessionId), token.getValue());
		super.storeAccessToken(token, authentication);
	}
}
