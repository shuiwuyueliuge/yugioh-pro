package cn.mayu.yugioh.basic.authorize;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AuthorizeApp {
	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(AuthorizeApp.class);
		app.run(args);
	}
}
