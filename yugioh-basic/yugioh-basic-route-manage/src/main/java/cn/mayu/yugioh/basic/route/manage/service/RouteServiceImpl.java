package cn.mayu.yugioh.basic.route.manage.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import cn.mayu.yugioh.basic.route.manage.model.dto.FilterDTO;
import cn.mayu.yugioh.basic.route.manage.model.dto.PredicateDTO;
import cn.mayu.yugioh.basic.route.manage.model.dto.RouteDTO;
import cn.mayu.yugioh.basic.route.manage.repository.RouteRepository;

@Service
public class RouteServiceImpl implements RouteService {
	
	@Autowired
	private RouteRepository routeRepository;
	
	@Autowired
	private RedisConnectionFactory redisConnectionFactory;
	
	@Autowired
	private DictService dictService;

	@Override
	public void redisCached() throws Exception {
		List<RouteDTO> list = routeRepository.findByStatus(0).stream().map(route -> {
			FilterDTO filter = new FilterDTO();
			filter.setName("StripPrefix");
			filter.getArgs().put("_genkey_0", "1");
			
			RouteDTO routeDTO = new RouteDTO();
			routeDTO.getFilters().add(filter);
			
			if (route.getPredicatesEntities() != null) {
				route.getPredicatesEntities().stream().forEach(predicates -> {
					PredicateDTO predicate = new PredicateDTO();
					predicate.setName(dictService.getValue(1, predicates.getArgName()));
					if (predicates.getArgValue().indexOf(",") != -1) {
						String[] values = predicates.getArgValue().split(",");
						Stream.of(values).forEach(value -> {
							predicate.getArgs().put(value, value);
						});
					} else {
						predicate.getArgs().put(predicates.getArgValue(), predicates.getArgValue());
					}
					
					routeDTO.getPredicates().add(predicate);
				});
			}
			
			routeDTO.setId(route.getSetviceId());
			routeDTO.setOrder(route.getOrder());
			routeDTO.setUri(route.getUri());
			return routeDTO;
		}).collect(Collectors.toList());
		
		byte[] serializedAuth = new ObjectMapper().writeValueAsBytes(list);
		byte[] authKey = "gateway:routes".getBytes();
		RedisConnection conn = redisConnectionFactory.getConnection();
		try {
			conn.openPipeline();
			conn.set(authKey, serializedAuth);
			conn.closePipeline();
		} finally {
			conn.close();
		}
	}
}
