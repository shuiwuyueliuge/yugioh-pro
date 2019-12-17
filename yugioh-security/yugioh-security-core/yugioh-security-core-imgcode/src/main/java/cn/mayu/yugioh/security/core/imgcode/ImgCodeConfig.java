package cn.mayu.yugioh.security.core.imgcode;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import cn.mayu.org.yugioh.security.core.base.validatecode.VaildateCodeFailureHandler;

public class ImgCodeConfig {

	@Bean
	@ConditionalOnMissingBean(value = { VaildateCodeFailureHandler.class })
	public VaildateCodeFailureHandler vaildateCodeFailureHandler() {
		return (request, response, exception) -> {
			response.getWriter().write("vaildate code failure");
		};
	}
}
