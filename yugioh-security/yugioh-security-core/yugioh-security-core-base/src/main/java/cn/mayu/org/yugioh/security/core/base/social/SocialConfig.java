package cn.mayu.org.yugioh.security.core.base.social;

import org.springframework.context.annotation.Bean;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.web.ConnectController;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.social.security.SpringSocialConfigurer;

public class SocialConfig {
	
	@Bean
	public SpringSocialConfigurer socialSecurityConfig(SocialProperty socialProperty) {
		SpringSocialConfigurer springSocialConfigurer = new SpringSocialConfigurer();
		springSocialConfigurer.signupUrl(socialProperty.getSignupUrl());
		return springSocialConfigurer;
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