package cn.mayu.yugioh.basic.authorize.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import cn.mayu.yugioh.basic.authorize.client.OauthClientDetailsHandlerChain;
import cn.mayu.yugioh.basic.authorize.exception.ClientNotFoundException;
import cn.mayu.yugioh.basic.authorize.model.entity.OauthClientDetails;
import cn.mayu.yugioh.basic.authorize.repository.OauthClientDetailsRepository;
import cn.mayu.yugioh.basic.authorize.util.HttpServletRequestHelper;
import cn.mayu.yugioh.common.core.util.Base64Util;

@Service
public class UserServiceImpl implements UserDetailsService {
	
	@Autowired
	private OauthClientDetailsRepository clientDetailsRepository;
	
	private static final String CLIENT_ID = "client_id";
	
	private static final String AUTHORIZATION_HEADER = "Authorization";
	
	@Autowired
	private OauthClientDetailsHandlerChain handlerChain;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		String clientId = getClientId();
		OauthClientDetails client = clientDetailsRepository.findByClientId(clientId);
		if (client == null) throw new ClientNotFoundException(String.format("%s not exists", clientId));
		return handlerChain.convertToUser(client, username);
	}
	
	private String getClientId() {
		String clientId = HttpServletRequestHelper.getParam(CLIENT_ID);
		if (clientId == null) {
			String header = HttpServletRequestHelper.getHeader(AUTHORIZATION_HEADER);
			if (header == null) throw new ClientNotFoundException("client_id param not found");
			String[] headers = header.split(" ");
			if (headers.length != 2) throw new ClientNotFoundException("client_id param not found");
			String base64 = headers[1];
			String clientIdAndSecret = Base64Util.decoder2Str(base64);
			String[] clientIdAndSecrets = clientIdAndSecret.split(":");
			if (clientIdAndSecrets.length != 2) throw new ClientNotFoundException("client_id param not found");
			clientId = clientIdAndSecrets[0];
		}
		
		return clientId;
	}
}