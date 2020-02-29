package cn.mayu.yugioh.security.application.config;

import org.springframework.security.oauth2.provider.ClientDetailsService;

@FunctionalInterface
public interface ClientDetailsServiceBuilder {

	ClientDetailsService build();
}
