package cn.mayu.yugioh.basic.gateway.route;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import cn.mayu.yugioh.basic.gateway.route.dto.RouteDTO;
import cn.mayu.yugioh.basic.gateway.route.service.RouteService;

@EnableCaching
@SpringBootApplication
@EnableTransactionManagement
@RestController
public class App implements CommandLineRunner {

	@Autowired
	private RouteService routeService;

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(App.class);
		app.setBannerMode(Banner.Mode.OFF);
		app.run(args);
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

	@Override
	public void run(String... args) throws Exception {
		routeService.initRoute();
	}
}
/**
{
	"status": 1,
	"routeDefinition": {
		"id": "test2",
		"tableId": null,
		"predicates": [{
			"name": "Path",
			"args": {
				"_genkey_0": "/**"
			}
		}, {
			"name": "Method",
			"args": {
				"_genkey_0": "POST",
				"_genkey_1": "GET"
			}
		}, {
			"name": "After",
			"args": {
				"_genkey_0": "2017-01-20T17:42:47.789-07:00[America/Denver]"
			}
		}, {
			"name": "Before",
			"args": {
				"_genkey_0": "2021-01-20T17:42:47.789-07:00[America/Denver]"
			}
		}, {
			"name": "Between",
			"args": {
				"_genkey_0": "2017-01-20T17:42:47.789-07:00[America/Denver]",
				"_genkey_1": "2017-01-21T17:42:47.789-07:00[America/Denver]"
			}
		}, {
			"name": "Cookie",
			"args": {
				"_genkey_0": "chocolate",
				"_genkey_1": "ch.p"
			}
		}, {
			"name": "Header",
			"args": {
				"_genkey_0": "X-Request-Id",
				"_genkey_1": "\\d+"
			}
		}, {
			"name": "Host",
			"args": {
				"_genkey_0": "**.somehost.org",
				"_genkey_1": "**.anotherhost.org"
			}
		}, {
			"name": "Query",
			"args": {
				"_genkey_0": "red",
				"_genkey_1": "gree."
			}
		}, {
			"name": "RemoteAddr",
			"args": {
				"_genkey_0": "192.168.1.1/24"
			}
		}, {
			"name": "Weight",
			"args": {
				"_genkey_0": "group1",
				"_genkey_1": "8"
			}
		}],
		"filters": [{
			"name": "StripPrefix",
			"args": {
				"_genkey_0": "1"
			}
		}],
		"uri": "http://www.baidu.com",
		"order": 1
	}
}
*/