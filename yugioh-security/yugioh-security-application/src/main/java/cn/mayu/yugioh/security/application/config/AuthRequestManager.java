package cn.mayu.yugioh.security.application.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import cn.mayu.org.yugioh.security.core.base.authorizerequest.RequestProvider;
import cn.mayu.org.yugioh.security.core.base.property.LoginProperty;

public class AuthRequestManager implements RequestProvider {
	
	@Autowired
	private LoginProperty loginProperty;

	@Override
	public void config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry) {
		registry.antMatchers(loginProperty.getProcessingUrl()).permitAll();
	}
}
