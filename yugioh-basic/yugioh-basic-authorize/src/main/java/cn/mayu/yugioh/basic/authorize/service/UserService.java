package cn.mayu.yugioh.basic.authorize.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return new User("ygo", new BCryptPasswordEncoder().encode("ygo"), authorities());
	}

	private Collection<GrantedAuthority> authorities() {        
		List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>();
		List<String> list = new ArrayList<String>();
		list.stream().forEach(role -> authList.add(new SimpleGrantedAuthority(role)));  
		return authList;
	}
}