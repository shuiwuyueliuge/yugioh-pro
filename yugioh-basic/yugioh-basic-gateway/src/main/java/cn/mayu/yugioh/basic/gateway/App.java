package cn.mayu.yugioh.basic.gateway;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.filter.FilterDefinition;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import cn.mayu.yugioh.basic.gateway.route.DynamicRouteServiceImpl;
import cn.mayu.yugioh.basic.gateway.route.GatewayFilterDefinition;
import cn.mayu.yugioh.basic.gateway.route.GatewayPredicateDefinition;
import cn.mayu.yugioh.basic.gateway.route.GatewayRouteDefinition;
import cn.mayu.yugioh.basic.gateway.route.RouteRepository;

@SpringBootApplication
public class App implements CommandLineRunner {
	
	@Autowired
	RouteRepository routeRepository;
	
	@Autowired
	private RedisConnectionFactory redisConnectionFactory;
	
	@Autowired
	private DynamicRouteServiceImpl dynamicRouteService;
	
	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(App.class);
		app.setBannerMode(Banner.Mode.OFF);
		app.run(args);
	}

	@Override
	public void run(String... args) throws Exception {
//		List<GatewayRouteDefinition> list = routeRepository.findByStatus(0).stream().map(route -> {
//			GatewayPredicateDefinition predicateDefinition = new GatewayPredicateDefinition();
//			predicateDefinition.setName("Path");
//			predicateDefinition.getArgs().put("pattern", route.getPredicates());
//	
//			GatewayFilterDefinition filterDefinition = new GatewayFilterDefinition();
//			filterDefinition.setName("StripPrefix");
//			filterDefinition.getArgs().put("_genkey_0", "1");
//			
//			GatewayRouteDefinition routeDefinition = new GatewayRouteDefinition();
//			routeDefinition.getFilters().add(filterDefinition);
//			routeDefinition.getPredicates().add(predicateDefinition);
//			routeDefinition.setId(route.getSetviceId());
//			routeDefinition.setOrder(route.getOrder());
//			routeDefinition.setUri(route.getUri());
//			return routeDefinition;
//		}).collect(Collectors.toList());
//		
//		byte[] serializedAuth = new ObjectMapper().writeValueAsBytes(list);
		byte[] authKey = "gateway:routes".getBytes();
		RedisConnection conn = redisConnectionFactory.getConnection();
//		try {
//			conn.openPipeline();
//			conn.set(authKey, serializedAuth);
//			conn.closePipeline();
//		} finally {
//			conn.close();
//		}

		byte[] res = conn.get(authKey);
		new ObjectMapper().readValue(res, new TypeReference<List<GatewayRouteDefinition>>() { }).stream().forEach(data -> {
			RouteDefinition definition = assembleRouteDefinition(data);
			String flag = this.dynamicRouteService.add(definition);
			System.out.println(flag);
		});
	}
	
	private RouteDefinition assembleRouteDefinition(GatewayRouteDefinition gwdefinition) {
		RouteDefinition definition = new RouteDefinition();
		definition.setId(gwdefinition.getId());
		definition.setOrder(gwdefinition.getOrder());

		// 设置断言
		List<PredicateDefinition> pdList = new ArrayList<>();
		List<GatewayPredicateDefinition> gatewayPredicateDefinitionList = gwdefinition.getPredicates();
		for (GatewayPredicateDefinition gpDefinition : gatewayPredicateDefinitionList) {
			PredicateDefinition predicate = new PredicateDefinition();
			predicate.setArgs(gpDefinition.getArgs());
			predicate.setName(gpDefinition.getName());
			pdList.add(predicate);
		}
		definition.setPredicates(pdList);

		// 设置过滤器
		List<FilterDefinition> filters = new ArrayList<>();
		List<GatewayFilterDefinition> gatewayFilters = gwdefinition.getFilters();
		for (GatewayFilterDefinition filterDefinition : gatewayFilters) {
			FilterDefinition filter = new FilterDefinition();
			filter.setName(filterDefinition.getName());
			filter.setArgs(filterDefinition.getArgs());
			filters.add(filter);
		}
		definition.setFilters(filters);

		URI uri = null;
		if (gwdefinition.getUri().startsWith("http")) {
			uri = UriComponentsBuilder.fromHttpUrl(gwdefinition.getUri()).build().toUri();
		} else {
			// uri为 lb://consumer-service 时使用下面的方法
			uri = URI.create(gwdefinition.getUri());
		}
		definition.setUri(uri);
		return definition;
	}
}
