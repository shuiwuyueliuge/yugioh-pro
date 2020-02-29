package cn.mayu.yugioh.basic.monitor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import cn.mayu.yugioh.basic.monitor.model.AccessToken;
import static cn.mayu.yugioh.common.core.util.RestUtil.*;
import static cn.mayu.yugioh.common.core.util.HttpServletRequestUtil.*;

@Service
public class UserServiceImpl implements UserDetailsService {
	
	private static final String USERNAME = "username";
	
	private static final String PASSWORD = "password";
	
	private static final String CLIENT_ID = "client_id";
	
	@Value("${monitor.clientId}")
	private String clientId;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Map<String, String> params = new HashMap<String, String>();
		String password = getParam(PASSWORD);
		if (password == null) return null;
		params.put(USERNAME, username);
		params.put(PASSWORD, password);
		params.put(CLIENT_ID, clientId);
		AccessToken accessToken = post("http://localhost:9600/login", params, AccessToken.class);
		return new User(username, "", new ArrayList<GrantedAuthority>());
	}
}

