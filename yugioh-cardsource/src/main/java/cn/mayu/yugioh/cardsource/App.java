package cn.mayu.yugioh.cardsource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class App {
	
	public static void main( String[] args ) {
		SpringApplication app = new SpringApplication(App.class);
		app.run(args);
	}
}
