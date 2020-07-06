package cn.mayu.yugioh.search;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableCaching
@EnableFeignClients(basePackages = { "cn.yugioh.common.facade.transform" })
public class SearchApp {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(SearchApp.class);
		app.run(args);
	}
}
