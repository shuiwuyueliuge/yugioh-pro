package cn.mayu.yugioh.basic.authorize.client;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import cn.mayu.org.yugioh.security.core.base.property.LoginProperty;
import cn.mayu.yugioh.basic.authorize.model.dto.UserLoginDto;
import cn.mayu.yugioh.basic.authorize.model.entity.OauthClientDetails;
import cn.mayu.yugioh.basic.authorize.model.entity.SysUser;
import cn.mayu.yugioh.basic.authorize.service.AuthUserDetails;
import cn.mayu.yugioh.basic.authorize.service.SysUserService;
import cn.mayu.yugioh.basic.authorize.util.HttpServletRequestHelper;

@Component
public class SysUserOauthClientDetailsHandler implements OauthClientDetailsHandler {

	@Autowired
	private LoginProperty loginProperty;
	
	@Autowired
	private SysUserService sysUserService;
	
	@Override
	public UserDetails convertToUser(OauthClientDetails client, String username) {
		UserLoginDto loginDto = getLoginDto(username, client.getClientId());
		SysUser user = sysUserService.login(loginDto);
		return new AuthUserDetails(user.getUsername(), user.getPassword(), user.getStatus(),new ArrayList<String>());
	}

	@Override
	public int clientType() {
		return 1;
	}
	
	private UserLoginDto getLoginDto(String username, String clientId) {
		String psw = HttpServletRequestHelper.getParam(loginProperty.getPasswordParameter());
		return new UserLoginDto(username, clientId, psw);
	}
}
