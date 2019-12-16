package cn.mayu.yugioh.security.browser.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;

import cn.mayu.org.yugioh.security.core.base.authorizerequest.RequestProvider;
import cn.mayu.yugioh.security.browser.property.LoginProperty;

public class LoginRequestProvider implements RequestProvider {
	
	@Autowired
	private LoginProperty loginProperty;

	@Override
	public void config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry) {
		if (loginProperty.getLoginPage() != null) {
			registry.antMatchers(loginProperty.getLoginPage()).permitAll();
			return;
		}
		
		registry.antMatchers("/login").permitAll();
	}
}
