package cn.mayu.yugioh.basic.authorize.service;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import lombok.ToString;

@ToString
public class AuthUserDetails implements UserDetails {

	private static final long serialVersionUID = 1L;
	
	private String password;
	private final String username;
	private final Collection<GrantedAuthority> authorities;
	private final int status;
	
	public AuthUserDetails(String password, 
						   String username,
						   int status, 
	                       List<String> authorities) {
		this.password = password;
		this.username = username;
		this.status = status;
		this.authorities = this.authorities(authorities);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return new BCryptPasswordEncoder().encode(password);
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return isEnabled();
	}

	@Override
	public boolean isAccountNonLocked() {
		return isEnabled();
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return isEnabled();
	}

	@Override
	public boolean isEnabled() {
		return status == 0 ? true : false;
	}
	
	private Collection<GrantedAuthority> authorities(List<String> authorities) {
		Set<GrantedAuthority> authList = new HashSet<GrantedAuthority>();
		authorities.stream().forEach(role -> authList.add(new SimpleGrantedAuthority(role)));
		return authList;
	}
}
