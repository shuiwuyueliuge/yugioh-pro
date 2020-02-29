package cn.mayu.yugioh.security.application.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import cn.mayu.org.yugioh.security.core.base.authorizerequest.RequestManager;
import cn.mayu.org.yugioh.security.core.base.property.LoginProperty;

@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
	
	@Autowired
	private AuthenticationFailureHandler failureHandler;

	@Autowired
	private AuthenticationSuccessHandler successHandler;
	
	@Autowired
	private RequestManager requestManager;
	
	@Autowired
	private LoginProperty loginProperty;

	@Override
	public void configure(HttpSecurity http) throws Exception {
		requestManager.config(http.authorizeRequests());
		loginConfig(http);
		
	}

	private void loginConfig(HttpSecurity http) throws Exception {
		http.formLogin()
			.loginProcessingUrl(loginProperty.getProcessingUrl())
			.usernameParameter(loginProperty.getUsernameParameter())
			.passwordParameter(loginProperty.getPasswordParameter())
			.failureHandler(failureHandler)
			.successHandler(successHandler);
	}
}
