package cn.mayu.yugioh.basic.gateway.route;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import cn.mayu.yugioh.basic.gateway.route.dto.RouteDTO;
import cn.mayu.yugioh.basic.gateway.route.service.RouteService;
import lombok.extern.slf4j.Slf4j;

@EnableCaching
@SpringBootApplication
@EnableScheduling
@Slf4j
@EnableTransactionManagement
@RestController
public class App {

	@Autowired
	private RouteService routeService;
	
	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(App.class);
		app.setBannerMode(Banner.Mode.OFF);
		app.run(args);
	}
	
	@Scheduled(cron = "${redis.task.corn}")
	public void task() {
		try {
			routeService.redisCached();
		} catch (Exception e) {
			log.error("redisCached error [{}]", e);
		}
	}
	
	@PostMapping("/route")
	public Integer addRoute(@RequestBody RouteDTO route) {
		return routeService.addRoute(route);
	}
	
	@PutMapping("/route")
	public Integer modifyRoute(@RequestBody RouteDTO route) {
		return routeService.modifyRoute(route);
	}
	
	@DeleteMapping("/route/{serviceId}")
	public String removeRoute(@PathVariable String serviceId) {
		return routeService.removeRoute(serviceId);
	}
}
