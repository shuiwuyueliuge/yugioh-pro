package cn.mayu.yugioh.cardsource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import cn.mayu.yugioh.cardsource.ourocg.OurocgService;

@SpringBootApplication
public class App implements CommandLineRunner {
	
	@Autowired
	private OurocgService ourocgService;
	
	public static void main( String[] args ) {
		SpringApplication app = new SpringApplication(App.class);
		app.run(args);
	}

	@Override
	public void run(String... args) throws Exception {
		ourocgService.translateOurocgData();
	}
}
