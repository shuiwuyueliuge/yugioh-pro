package cn.mayu.yugioh.basic.authorize.config;

import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ReRedisTokenStore extends RedisTokenStore {
	
	private RedisConnectionFactory connectionFactory;
	
	private static final String AUTH_JSON = "auth_json:";

	public ReRedisTokenStore(RedisConnectionFactory connectionFactory) {
		super(connectionFactory);
		this.connectionFactory = connectionFactory;
	}

	@Override
	public void storeAccessToken(OAuth2AccessToken token, OAuth2Authentication authentication) {
		super.storeAccessToken(token, authentication);
		byte[] serializedAuth = null;
		try {
			serializedAuth = new ObjectMapper().writeValueAsBytes(authentication);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		byte[] authKey = (AUTH_JSON + token.getValue()).getBytes();
		RedisConnection conn = connectionFactory.getConnection();
		try {
			conn.openPipeline();
			conn.set(authKey, serializedAuth);
			if (token.getExpiration() != null) {
				int seconds = token.getExpiresIn();
				conn.expire(authKey, seconds);
			}
			conn.closePipeline();
		} finally {
			conn.close();
		}
	}
}
