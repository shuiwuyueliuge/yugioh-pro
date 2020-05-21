package cn.mayu.yugioh.basic.authorize.client;

import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import cn.mayu.yugioh.basic.authorize.exception.ClientNotFoundException;
import cn.mayu.yugioh.basic.authorize.model.entity.OauthClientDetails;

@Component
public class OauthClientDetailsHandlerChain {
	
	@Autowired
	private Set<OauthClientDetailsHandler> handlers;

	public UserDetails convertToUser(OauthClientDetails client, String username) {
		for (OauthClientDetailsHandler handler : handlers) {
			if (handler.clientType() != client.getType()) {
				continue;
			}
			
			return handler.convertToUser(client, username);
		}
		
		throw new ClientNotFoundException(String.format("%s type [%s] error", client.getId(), client.getType()));
	}
}
