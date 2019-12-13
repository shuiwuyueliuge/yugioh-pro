package cn.mayu.yugioh.security.browser.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import cn.mayu.yugioh.security.browser.property.LoginProperty;

@EnableWebSecurity
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired(required = false)
	private LoginProperty loginProperty;
	
	@Autowired(required = false)
	private AuthenticationFailureHandler failureHandler;

	@Autowired(required = false)
	private AuthenticationSuccessHandler successHandler;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		if (loginProperty != null) {
			loginConfig(http);
		}
	}
	
	private void loginConfig(HttpSecurity http) throws Exception {
	    http.formLogin()
			.loginPage(loginProperty.getLoginPage())
			.loginProcessingUrl(loginProperty.getProcessingUrl())
			.usernameParameter(loginProperty.getUsernameParameter())
			.passwordParameter(loginProperty.getPasswordParameter())
	        .failureHandler(failureHandler)
		    .successHandler(successHandler);
	}
}
