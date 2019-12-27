package cn.mayu.org.yugioh.security.core.base.adapter;

import java.util.Set;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;
import cn.mayu.org.yugioh.security.core.base.validatecode.VaildateCodeFailureHandler;
import cn.mayu.org.yugioh.security.core.base.validatecode.ValidateCodeManager;
import cn.mayu.org.yugioh.security.core.base.validatecode.ValidateCodeProcessorHolder;
import cn.mayu.org.yugioh.security.core.base.validatecode.ValidateFilter;

public class ValidateConfigurerAdapter extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

	private ValidateCodeManager codeManager;

	private Set<RequestMatcher> requestMatcher;

	private VaildateCodeFailureHandler failHandler;
	
	private ValidateCodeProcessorHolder processorHolder;

	public ValidateConfigurerAdapter(ValidateCodeProcessorHolder processorHolder, 
									 Set<RequestMatcher> requestMatcher, 
									 ValidateCodeManager codeManager, 
									 VaildateCodeFailureHandler failHandler) {
		this.failHandler = failHandler;
		this.processorHolder = processorHolder;
		this.codeManager = codeManager;
		this.requestMatcher = requestMatcher;
	}

	@Override
	public void configure(HttpSecurity builder) throws Exception {
		builder.addFilterBefore(new ValidateFilter(processorHolder, codeManager, failHandler, requestMatcher), UsernamePasswordAuthenticationFilter.class);
	}
}
