package cn.mayu.yugioh.basic.authorize.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import java.util.Base64;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import cn.mayu.yugioh.basic.authorize.IJwtTokenStore;

/**
 * authcode-server
 * http://localhost:9600/oauth/authorize?client_id=client&redirect_uri=http://localhost:9001/callback&response_type=code&scope=read
 * curl -X POST --user clientapp:112233 http://localhost:8080/oauth/token -H "content-type: application/x-www-form-urlencoded Authorization: Basic base64(client_id:secret)" -d "code=8uYpdo&grant_type=authorization_code&redirect_uri=http%3A%2F%2Flocalh ost%3A9001%2Fcallback&scope=read"
 * http://xxx/xxx?access_token=
 *
 * client-server
 * curl -X POST "http://localhost:9600/oauth/token" -d "grant_type=client_credentials&scope=devops -H "content-type: application/x-www-form-urlencoded Authorization: Basic base64(client_id:secret)" -d "grant_type=client_credentials&scope=read"
 * http://xxx/xxx?access_token=
 *
 * implicit-server
 * http://localhost:9600/oauth/authorize?client_id=client3&redirect_uri=http://localhost:9001/callback&response_type=token&scope=read&state=abc
 * 通过验证后返回：http://localhost:9001/callback#access_token=7017ff81-0660-4e3a-9c5d-f1ef8fa88d92&token_type=bearer&state=abc&expires_in=43199
 * http://xxx/xxx?access_token=
 *
 * password-server
 * curl -X POST http://localhost:9600/oauth/token -H "accept: application/json" -H "content-type: application/x-www-form-urlencoded Authorization: Basic base64(client_id:secret)" -d "grant_type=password&username=demoUser1&password=123456&scope=read"
 * http://xxx/xxx?access_token=
 */
@Configuration
public class AuthServerAdapter extends AuthorizationServerConfigurerAdapter {

	@Autowired
	private DataSource dataSource;
	
	@Autowired
    private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtAccessTokenConverter jwtAccessTokenConverter;
	
	//@Bean
    public TokenStore tokenStore() {
        return new JdbcTokenStore(dataSource);
    }
    
    @Bean
	public TokenStore jwtTokenStore(JwtAccessTokenConverter tokenConverter) {
		return new IJwtTokenStore(tokenConverter, dataSource);
	}

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.jdbc(dataSource);
	}

	@Override
	public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
		// 允许表单认证
		oauthServer.allowFormAuthenticationForClients();
		// 允许check_token访问
		oauthServer.checkTokenAccess("permitAll()");
	}
	
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {	
		TokenEnhancerChain enhancerChain = new TokenEnhancerChain();
        List<TokenEnhancer> enhancerList = new ArrayList<TokenEnhancer>();
        enhancerList.add(enhancer());
        enhancerList.add(jwtAccessTokenConverter);
        enhancerChain.setTokenEnhancers(enhancerList);
		endpoints.tokenStore(jwtTokenStore(jwtAccessTokenConverter))
		         .authenticationManager(authenticationManager)
		         .accessTokenConverter(jwtAccessTokenConverter)
		         .tokenEnhancer(enhancerChain)
		         .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST);
	}
	
	@Bean
	@ConditionalOnMissingBean(name = "enhancer")
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

//	public static void main(String[] args) {
//		System.out.println("Basic " + Base64.getEncoder().encodeToString("client4:secret".getBytes()));
//		System.out.println(new BCryptPasswordEncoder().encode("secret"));
//	}
}
