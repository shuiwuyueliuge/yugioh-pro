package cn.mayu.yugioh.basic.authorize.client;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import cn.mayu.yugioh.security.core.base.property.LoginProperty;
import cn.mayu.yugioh.basic.authorize.model.dto.UserLoginDto;
import cn.mayu.yugioh.basic.authorize.model.entity.OauthClientDetails;
import cn.mayu.yugioh.basic.authorize.model.entity.User;
import cn.mayu.yugioh.basic.authorize.service.AuthUserDetails;
import cn.mayu.yugioh.basic.authorize.service.ConsumerService;
import cn.mayu.yugioh.basic.authorize.util.HttpServletRequestHelper;

@Component
public class ConsumerOauthClientDetailsHandler implements OauthClientDetailsHandler {
	
	@Autowired
	private LoginProperty loginProperty;
	
	@Autowired
	private ConsumerService consumerService;

	@Override
	public UserDetails convertToUser(OauthClientDetails client, String username) {
		UserLoginDto loginDto = getLoginDto(username, client.getClientId());
		User user = consumerService.login(loginDto);
		return new AuthUserDetails(user.getUsername(), user.getPassword(), user.getStatus(), new ArrayList<String>());
	}

	@Override
	public int clientType() {
		return 0;
	}
	
	private UserLoginDto getLoginDto(String username, String clientId) {
		String psw = HttpServletRequestHelper.getParam(loginProperty.getPasswordParameter());
		return new UserLoginDto(username, clientId, psw);
	}
}
