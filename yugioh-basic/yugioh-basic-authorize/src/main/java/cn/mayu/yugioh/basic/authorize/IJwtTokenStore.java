package cn.mayu.yugioh.basic.authorize;

import javax.sql.DataSource;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

public class IJwtTokenStore extends JwtTokenStore {
	
	private JdbcTokenStore jdbcTokenStore;

	public IJwtTokenStore(JwtAccessTokenConverter jwtTokenEnhancer, DataSource dataSource) {
		super(jwtTokenEnhancer);
		this.jdbcTokenStore = new JdbcTokenStore(dataSource);
	}

	@Override
	public void storeAccessToken(OAuth2AccessToken token, OAuth2Authentication authentication) {
		jdbcTokenStore.storeAccessToken(token, authentication);
	}
	
	@Override
	public void storeRefreshToken(OAuth2RefreshToken refreshToken, OAuth2Authentication authentication) {
		jdbcTokenStore.storeRefreshToken(refreshToken, authentication);
	}
}
