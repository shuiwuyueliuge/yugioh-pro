package cn.mayu.yugioh.security.core.social;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.web.ConnectController;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.social.security.SpringSocialConfigurer;

public class SocialConfig {
	
	@Bean
	@ConditionalOnMissingBean(value = { AuthenticationSuccessHandler.class })
	public AuthenticationSuccessHandler a() {
		return (request, response, authentication) -> {
			System.out.println(authentication.getName());
		};
	}
	
	@Bean
	public SpringSocialConfigurer socialSecurityConfig(SocialProperty socialProperty, AuthenticationSuccessHandler successHandler) {
//		SocialConfigAdapter springSocialConfigurer = new SocialConfigAdapter(successHandler);
//		springSocialConfigurer.signupUrl(socialProperty.getSignupUrl());
//		return springSocialConfigurer;
		SpringSocialConfigurer a = new SpringSocialConfigurer();
		a.signupUrl(socialProperty.getSignupUrl());
		return a;
	}

	@Bean
	public ProviderSignInUtils providerSignInUtils(ConnectionFactoryLocator factoryLocator,
			UsersConnectionRepository connectionRepository) {
		return new ProviderSignInUtils(factoryLocator, connectionRepository);
	}

	@Bean
	public ConnectController connectController(ConnectionFactoryLocator connectionFactoryLocator,
			ConnectionRepository connectionRepository) {
		return new ConnectController(connectionFactoryLocator, connectionRepository);
	}
}