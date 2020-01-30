package cn.mayu.yugioh.sync.local;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import cn.mayu.yugioh.sync.local.service.IndexService;

@SpringBootApplication
@EnableAsync
public class App implements CommandLineRunner {
	
	@Autowired
	private IndexService indexservice;

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(App.class);
		app.run(args);
	}

	@Override
	public void run(String... args) throws Exception {
		indexservice.indexCache();
	}
}
