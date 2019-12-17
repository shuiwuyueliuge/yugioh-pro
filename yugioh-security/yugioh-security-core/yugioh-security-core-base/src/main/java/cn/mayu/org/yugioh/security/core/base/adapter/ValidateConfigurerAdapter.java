package cn.mayu.org.yugioh.security.core.base.adapter;

import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import cn.mayu.org.yugioh.security.core.base.validatecode.VaildateCodeFailureHandler;
import cn.mayu.org.yugioh.security.core.base.validatecode.ValidateCodeProcessorHolder;
import cn.mayu.org.yugioh.security.core.base.validatecode.ValidateFilter;

public class ValidateConfigurerAdapter extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

	private ValidateCodeProcessorHolder holder;

	private VaildateCodeFailureHandler failHandler;

	public ValidateConfigurerAdapter(ValidateCodeProcessorHolder holder, VaildateCodeFailureHandler failHandler) {
		this.holder = holder;
		this.failHandler = failHandler;
	}

	@Override
	public void configure(HttpSecurity builder) throws Exception {
		builder.addFilterBefore(new ValidateFilter(holder, failHandler), UsernamePasswordAuthenticationFilter.class);
	}
}
