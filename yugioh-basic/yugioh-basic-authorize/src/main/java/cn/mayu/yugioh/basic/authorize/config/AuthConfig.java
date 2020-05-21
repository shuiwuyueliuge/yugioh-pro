package cn.mayu.yugioh.basic.authorize.config;

import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import cn.mayu.yugioh.basic.authorize.property.RsaProperty;
import cn.mayu.yugioh.security.application.config.ClientDetailsServiceBuilder;

@Configuration
public class AuthConfig {

	@Bean
	public JwtAccessTokenConverter jwtAccessTokenConverter(RsaProperty rsaPropertie) {
		JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
		converter.setSigningKey(rsaPropertie.getPrivateKey());
		converter.setVerifierKey(rsaPropertie.getPublicKey());
		return converter;
	}

	@Bean
	public TokenStore redisTokenStore(RedisConnectionFactory connectionFactory) {
		return new ReRedisTokenStore(connectionFactory);
	}

	@Bean
	public TokenEnhancerChain enhancerChain(JwtAccessTokenConverter accessTokenConverter) {
		TokenEnhancerChain enhancerChain = new TokenEnhancerChain();
		List<TokenEnhancer> enhancerList = new ArrayList<TokenEnhancer>();
		// enhancerList.add(new JwtTokenEnhancer());
		enhancerList.add(accessTokenConverter);
		enhancerChain.setTokenEnhancers(enhancerList);
		return enhancerChain;
	}

	@Bean
	public ClientDetailsServiceBuilder jdbc(DataSource dataSource) {
		return () -> new JdbcClientDetailsService(dataSource);
	}
}