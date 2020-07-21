package cn.mayu.yugioh.basic.gateway.daedalus;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GateWayApp {
	
	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(GateWayApp.class);
		app.setBannerMode(Banner.Mode.OFF);
		app.run(args);
	}
}
