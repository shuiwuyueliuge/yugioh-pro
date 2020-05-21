package cn.mayu.yugioh.cardsource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CardSourceApp {
	
	public static void main( String[] args ) {
		SpringApplication app = new SpringApplication(CardSourceApp.class);
		app.run(args);
	}
}
