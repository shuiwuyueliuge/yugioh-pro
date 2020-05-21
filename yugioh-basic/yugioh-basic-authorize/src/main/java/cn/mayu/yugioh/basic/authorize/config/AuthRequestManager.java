package cn.mayu.yugioh.basic.authorize.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import cn.mayu.org.yugioh.security.core.base.authorizerequest.RequestProvider;

@Configuration
public class AuthRequestManager implements RequestProvider {

	@Override
	public void config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry) {
		registry.antMatchers("/login").permitAll();
	}
}
