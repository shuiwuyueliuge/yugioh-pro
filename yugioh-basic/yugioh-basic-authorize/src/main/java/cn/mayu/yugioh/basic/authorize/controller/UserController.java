package cn.mayu.yugioh.basic.authorize.controller;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import cn.mayu.yugioh.basic.authorize.service.AuthUserDetails;
import cn.mayu.yugioh.common.core.api.ApiRestWrapper;
import cn.mayu.yugioh.common.core.api.UserDetialsVO;

@RestController
@RequestMapping("/user")
@ApiRestWrapper
public class UserController {
	
	@Autowired
	private TokenStore tokenStore;

	@RequestMapping("/principal")
	public UserDetialsVO getPrincipalDetails(@RequestParam("access_token") String accessToken) {
		OAuth2Authentication authentication = tokenStore.readAuthentication(accessToken);
		UserDetails authorities = (AuthUserDetails) authentication.getPrincipal();
		List<String> list = authorities.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
		UserDetialsVO userDetialsVO = new UserDetialsVO(authorities.getUsername(), authorities.getPassword(), list, authorities.isAccountNonLocked());
		return userDetialsVO;
	}
}
