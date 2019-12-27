package cn.mayu.yugioh.security.core.sms;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import cn.mayu.org.yugioh.security.core.base.config.EhcacheConfig;
import cn.mayu.org.yugioh.security.core.base.validatecode.EhcacheValidateCodeManager;
import cn.mayu.org.yugioh.security.core.base.validatecode.VaildateCodeFailureHandler;
import cn.mayu.org.yugioh.security.core.base.validatecode.ValidateCodeManager;

public class SmsCodeConfig {
	
	@Bean
	public RequestMatcher smsRequestMatcher(SmsValidateCodeProperty loginProperty) {
		return new AntPathRequestMatcher(loginProperty.getProcessingUrl(), "POST");
	}

	@Bean
	@ConditionalOnMissingBean(value = { VaildateCodeFailureHandler.class })
	public VaildateCodeFailureHandler smsVaildateCodeFailureHandler() {
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
	@ConditionalOnMissingBean(value = { SmsValidateCodeGenerator.class })
	public SmsValidateCodeGenerator smsValidateCodeGenerator() {
		return new SmsValidateCodeGenerator();
	}
	
	@Bean
	@ConditionalOnMissingBean(value = { SmsCodeProcesser.class })
	public SmsCodeProcesser smsCodeProcesser(SmsValidateCodeGenerator imgValidateCodeGenerator, ValidateCodeManager manager) {
		return new SmsCodeProcesser(imgValidateCodeGenerator, manager);
	}
}