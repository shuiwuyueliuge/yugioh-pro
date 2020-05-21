package cn.mayu.yugioh.basic.authorize.client;

import org.springframework.security.core.userdetails.UserDetails;
import cn.mayu.yugioh.basic.authorize.model.entity.OauthClientDetails;

public interface OauthClientDetailsHandler {

	UserDetails convertToUser(OauthClientDetails client, String username);
	
	int clientType();
}
