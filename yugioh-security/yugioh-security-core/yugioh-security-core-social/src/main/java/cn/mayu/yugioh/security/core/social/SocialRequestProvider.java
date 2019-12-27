package cn.mayu.yugioh.security.core.social;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;

import cn.mayu.org.yugioh.security.core.base.authorizerequest.RequestProvider;

public class SocialRequestProvider implements RequestProvider {

	@Override
	public void config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry) {
		registry.antMatchers("/social/**", "/auth/**").permitAll();
	}
}
