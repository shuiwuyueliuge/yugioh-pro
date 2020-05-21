//package cn.mayu.yugioh.basic.authorize.config;
//
//import java.util.HashMap;
//import java.util.Map;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
//import org.springframework.security.oauth2.common.OAuth2AccessToken;
//import org.springframework.security.oauth2.provider.OAuth2Authentication;
//import org.springframework.security.oauth2.provider.token.TokenEnhancer;
//
//public class JwtTokenEnhancer implements TokenEnhancer {
//
//	@Override
//	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
//		Map<String, Object> map = new HashMap<String, Object>();
//		UserDetails user = (UserDetails) authentication.getPrincipal();
//		DefaultOAuth2AccessToken token = ((DefaultOAuth2AccessToken) accessToken);
//		token.setAdditionalInformation(map);
//		return token;
//	}
//}