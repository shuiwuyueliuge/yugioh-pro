package cn.mayu.yugioh.cardsource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import cn.mayu.yugioh.cardsource.ourocg.OurocgService;

@SpringBootApplication
@EnableScheduling
@RestController
public class App {
	
	public static void main( String[] args ) {
		SpringApplication app = new SpringApplication(App.class);
		app.run(args);
	}

	@Autowired
	OurocgService a;
	
	@RequestMapping("/test")
	public Object te() {
		try {
			a.publishPackageDetail("/aaaaa", 2);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 123;
	}
}
