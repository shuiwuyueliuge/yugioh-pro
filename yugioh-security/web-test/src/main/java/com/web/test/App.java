package com.web.test;

import cn.mayu.yugioh.security.core.base.authorizerequest.RequestProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@SpringBootApplication
public class App {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(App.class);
		app.run(args);
	}

	@RequestMapping("/t1")
	public Object test1() {
		return 11;
	}

	@RequestMapping("/t2")
	public Object test2() {
		return 22;
	}

	@Bean
	public RequestProvider init() {
		return registry -> registry.antMatchers("/t1").permitAll();
	}
}
