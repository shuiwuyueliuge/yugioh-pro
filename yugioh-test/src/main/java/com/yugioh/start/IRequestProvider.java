package com.yugioh.start;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

import cn.mayu.org.yugioh.security.core.base.authorizerequest.RequestProvider;

@Component
public class IRequestProvider implements RequestProvider {

	@Override
	public void config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry) {
		registry.antMatchers("/code/**", "/code/result").permitAll();
	}
}