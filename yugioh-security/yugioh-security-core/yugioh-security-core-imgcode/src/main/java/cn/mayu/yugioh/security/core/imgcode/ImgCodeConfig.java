package cn.mayu.yugioh.security.core.imgcode;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import cn.mayu.org.yugioh.security.core.base.config.EhcacheConfig;
import cn.mayu.org.yugioh.security.core.base.validatecode.EhcacheValidateCodeManager;
import cn.mayu.org.yugioh.security.core.base.validatecode.VaildateCodeFailureHandler;
import cn.mayu.org.yugioh.security.core.base.validatecode.ValidateCodeManager;
import cn.mayu.yugioh.security.browser.property.LoginProperty;

public class ImgCodeConfig {
	
	@Bean
	public RequestMatcher imgRequestMatcher(LoginProperty loginProperty) {
		return new AntPathRequestMatcher(loginProperty.getProcessingUrl(), "POST");
	}

	@Bean
	@ConditionalOnMissingBean(value = { VaildateCodeFailureHandler.class })
	public VaildateCodeFailureHandler vaildateCodeFailureHandler() {
		return (request, response, exception) -> {
			response.getWriter().write("vaildate code failure");
		};
	}
	
	@Bean
	@ConditionalOnMissingBean(value = { ValidateCodeManager.class })
	public ValidateCodeManager validateCodeManager(EhcacheConfig ehcacheConfig) {
		return new EhcacheValidateCodeManager(ehcacheConfig);
	}
	
	@Bean
	@ConditionalOnMissingBean(value = { ImgValidateCodeGenerator.class })
	public ImgValidateCodeGenerator imgValidateCodeGenerator() {
		return new ImgValidateCodeGenerator();
	}
	
	@Bean
	@ConditionalOnMissingBean(value = { ImgCodeProcesser.class })
	public ImgCodeProcesser imgCodeProcesser(ImgValidateCodeGenerator imgValidateCodeGenerator, ValidateCodeManager manager) {
		return new ImgCodeProcesser(imgValidateCodeGenerator, manager);
	}
}
