package cn.mayu.yugioh.basic.monitor.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import cn.mayu.org.yugioh.security.core.base.authorizerequest.RequestProvider;
import de.codecentric.boot.admin.server.config.AdminServerProperties;

@Configuration
public class AdminConfig {

	@Bean
	public AuthenticationSuccessHandler successHandler(AdminServerProperties adminServerProperties) {
		SavedRequestAwareAuthenticationSuccessHandler successHandler = new SavedRequestAwareAuthenticationSuccessHandler();
        successHandler.setTargetUrlParameter("redirectTo");
        successHandler.setDefaultTargetUrl(adminServerProperties.getContextPath() + "/");
        return successHandler;
	}
	
	@Bean
	public RequestProvider requestProvider(AdminServerProperties adminServerProperties) {
        return new AdminRequestProvider(adminServerProperties.getContextPath());
	}
}

class AdminRequestProvider implements RequestProvider {
	
	private String adminContextPath;

	public AdminRequestProvider(String adminContextPath) {
		this.adminContextPath = adminContextPath;
	}

	@Override
	public void config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry) {
		registry.antMatchers(adminContextPath + "/assets/**").permitAll();
	}}
