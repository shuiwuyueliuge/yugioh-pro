package cn.mayu.yugioh.basic.route.manage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import cn.mayu.yugioh.basic.route.manage.service.DictService;
import cn.mayu.yugioh.basic.route.manage.service.RouteService;
import reactor.core.publisher.Flux;

@EnableCaching
@SpringBootApplication
@RestController
public class App {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(App.class);
		app.setBannerMode(Banner.Mode.OFF);
		app.run(args);
	}

	@Autowired
	RouteService ds;

	@RequestMapping("/test")
	public Flux<Object> test() throws Exception {ds.redisCached();
		return Flux.just("");
	}
}
