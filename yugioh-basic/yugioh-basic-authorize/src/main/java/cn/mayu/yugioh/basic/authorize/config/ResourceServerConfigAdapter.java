package cn.mayu.yugioh.basic.authorize.config;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class ResourceServerConfigAdapter extends ResourceServerConfigurerAdapter {
	
	@Value("${config.oauth2.privateKey}")
	private String privateKey;
	
	@Value("${config.oauth2.publicKey}")
	private String publicKey;
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
            .anyRequest()
            .authenticated()
            .and()
            .requestMatchers()
            .antMatchers("*");
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public JwtAccessTokenConverter jwtAccessTokenConverter() {
		JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
		converter.setSigningKey(privateKey);
		converter.setVerifierKey(publicKey);
		return converter;
	}
	
	@Bean
	public TokenStore redisTokenStore(RedisConnectionFactory connectionFactory) {
		return new RedisTokenStore(connectionFactory);
	}
	
	@Bean
	public TokenEnhancer enhancer() {
		return new TokenEnhancer() {
			
			@Override
			public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("test", "test1");
				DefaultOAuth2AccessToken token = ((DefaultOAuth2AccessToken) accessToken);
				token.setAdditionalInformation(map);
				return token;
			}
		};
	} 
}
