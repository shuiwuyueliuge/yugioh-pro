package cn.mayu.yugioh.security.browser.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import cn.mayu.org.yugioh.security.core.base.property.LoginProperty;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BrowserBeanConfig {

	@Bean
	@ConditionalOnMissingBean(value = { SessionInformationExpiredStrategy.class })
	public SessionInformationExpiredStrategy expiredStrategy(LoginProperty loginProperty) {
		return event -> {
			log.warn("This session has been expired (possibly due to multiple concurrent logins being attempted as the same user).");
			event.getResponse().sendRedirect(loginProperty.getLoginPage());
		};
	}
}
