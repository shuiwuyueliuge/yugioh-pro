package cn.mayu.yugioh.transform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableAsync
@EnableTransactionManagement
public class App  {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(App.class);
		app.run(args);
	}
}
