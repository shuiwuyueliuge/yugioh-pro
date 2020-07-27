package cn.mayu.yugioh.cardsource.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@SpringBootApplication
@EnableFeignClients(basePackages = {"cn.mayu.yugioh.common.facade.cardsource"})
@EnableReactiveMongoRepositories(basePackages = { "cn.mayu.yugioh.cardsource.basic.repository" })
public class CardSourceApp {
	
	public static void main( String[] args ) {
		SpringApplication app = new SpringApplication(CardSourceApp.class);
		app.run(args);
	}
}
