package cn.mayu.yugioh.basic.registry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class RegisterApp {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(RegisterApp.class);
		app.run(args);
	}
}
