package cn.mayu.yugioh.cardsource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import cn.mayu.yugioh.cardsource.ourocg.OurocgDataCenter;
import cn.mayu.yugioh.cardsource.repository.OurocgRepository;

@SpringBootApplication
@EnableScheduling
public class App {
	
	public static void main( String[] args ) {
		SpringApplication app = new SpringApplication(App.class);
		app.run(args);
	}

	@Autowired
	OurocgDataCenter center;
	
	@Autowired
	OurocgRepository or;
}
