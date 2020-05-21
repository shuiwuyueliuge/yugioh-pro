package cn.mayu.yugioh.basic.authorize.client;

import java.util.ArrayList;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import cn.mayu.yugioh.basic.authorize.model.entity.OauthClientDetails;

@Component
public class ThirdOauthClientDetailsHandler implements OauthClientDetailsHandler {

	@Override
	public UserDetails convertToUser(OauthClientDetails client, String username) {
		return new User("ygo", new BCryptPasswordEncoder().encode("ygo"), new ArrayList<GrantedAuthority>());
	}

	@Override
	public int clientType() {
		return 2;
	}
}
