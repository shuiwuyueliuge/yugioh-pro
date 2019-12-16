package cn.mayu.org.yugioh.security.core.base.authorizerequest;

import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;

public class DefaultRequestManager implements RequestManager {
	
	@Autowired
	private Set<RequestProvider> providers;

	@Override
	public void config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry) {
		providers.forEach(provider -> provider.config(registry));
		registry.anyRequest().authenticated();
	}
}
