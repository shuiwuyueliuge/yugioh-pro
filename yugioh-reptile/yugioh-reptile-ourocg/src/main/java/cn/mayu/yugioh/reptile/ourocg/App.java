package cn.mayu.yugioh.reptile.ourocg;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@RestController
@RefreshScope
@EnableFeignClients
public class App {
	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(App.class);
		app.setBannerMode(Banner.Mode.OFF);
		app.run(args);
	}
	
	//@Value("${com.didispace.message}")
	//private String s;
	
	@Bean
	@LoadBalanced
	public RestTemplate initR() {
		return new RestTemplate();
	}
	
	@Autowired
	TestClient tc;
	
	@RequestMapping("/ss")
	public String test() {
		return tc.test();
	}
}
