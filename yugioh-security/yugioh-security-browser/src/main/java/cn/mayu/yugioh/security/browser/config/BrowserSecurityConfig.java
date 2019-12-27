package cn.mayu.yugioh.security.browser.config;

import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.SessionManagementConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import cn.mayu.org.yugioh.security.core.base.authorizerequest.RequestManager;
import cn.mayu.org.yugioh.security.core.base.property.LoginProperty;
import cn.mayu.yugioh.security.browser.property.RememberMeProperty;
import cn.mayu.yugioh.security.browser.property.SessionProperty;

@EnableWebSecurity
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private LoginProperty loginProperty;
	
	@Autowired
	private SessionProperty sessionProperty;
	
	@Autowired
	private RequestManager requestManager;
	
	@Autowired(required = false)
	private AuthenticationFailureHandler failureHandler;

	@Autowired(required = false)
	private AuthenticationSuccessHandler successHandler;
	
	@Autowired(required = false)
	private PersistentTokenRepository persistentTokenRepository;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private RememberMeProperty rememberMeProperty;
	
	@Autowired(required = false)
	private InvalidSessionStrategy invalidSessionStrategy;
	
	@Autowired
	private SessionInformationExpiredStrategy sessionInformationExpiredStrategy;
	
	@Autowired(required = false)
	private Set<SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity>> securityConfigurerAdapter;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		requestManager.config(http.authorizeRequests());
		http.csrf().disable();
		loginConfig(http);
		rememberMeConfig(http);
		sessionConfig(http);
		apply(http);
	}
	
	private void loginConfig(HttpSecurity http) throws Exception {
		try {
			if (loginProperty.getLoginPage() == null) {
				http.formLogin().and().httpBasic();
				return;
			}
			
		    http.formLogin()
				.loginPage(loginProperty.getLoginPage())
				.loginProcessingUrl(loginProperty.getProcessingUrl())
				.usernameParameter(loginProperty.getUsernameParameter())
				.passwordParameter(loginProperty.getPasswordParameter());
		} finally {
			if (failureHandler != null) http.formLogin().failureHandler(failureHandler);
			if (successHandler != null) http.formLogin().successHandler(successHandler);
		}
	}
	
	private void rememberMeConfig(HttpSecurity http) throws Exception {
		if (persistentTokenRepository != null) {
			http.rememberMe()
			    .tokenRepository(persistentTokenRepository)
				.tokenValiditySeconds(rememberMeProperty.getTokenValiditySeconds())
				.userDetailsService(userDetailsService);
		}
	}
	
	private void sessionConfig(HttpSecurity http) throws Exception {
		SessionManagementConfigurer<HttpSecurity> configurer = http.sessionManagement();
		if (invalidSessionStrategy != null) configurer.invalidSessionStrategy(invalidSessionStrategy);//session超时
		
		//并发登陆
		configurer.maximumSessions(sessionProperty.getMaximumSessions())
		          .maxSessionsPreventsLogin(sessionProperty.isMaxSessionsPreventsLogin())
		          .expiredSessionStrategy(sessionInformationExpiredStrategy);
	}
	
	private void apply(HttpSecurity http) throws Exception {
		if (securityConfigurerAdapter == null || securityConfigurerAdapter.size() == 0) {
			return;
		}
		
		for (SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> adapter : securityConfigurerAdapter) {
			http.apply(adapter);
		}
	}
}
