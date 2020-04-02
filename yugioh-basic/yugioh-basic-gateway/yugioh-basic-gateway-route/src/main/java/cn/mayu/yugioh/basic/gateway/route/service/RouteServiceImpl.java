package cn.mayu.yugioh.basic.gateway.route.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import cn.mayu.yugioh.basic.gateway.route.dto.FilterDTO;
import cn.mayu.yugioh.basic.gateway.route.dto.PredicateDTO;
import cn.mayu.yugioh.basic.gateway.route.dto.RouteDTO;
import static cn.mayu.yugioh.basic.gateway.route.entity.BeanConvertFactory.*;
import cn.mayu.yugioh.basic.gateway.route.entity.PredicatesEntity;
import cn.mayu.yugioh.basic.gateway.route.entity.RouteConverter;
import cn.mayu.yugioh.basic.gateway.route.entity.RouteEntity;
import cn.mayu.yugioh.basic.gateway.route.repository.RouteRepository;

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
				for (int i = 0; i < route.getPredicatesEntities().size(); i++) {
					String key = "_genkey_";
					PredicatesEntity predicates = route.getPredicatesEntities().get(i);
					PredicateDTO predicate = new PredicateDTO();
					predicate.setName(dictService.getValue(1, predicates.getArgName()));
					if (predicates.getArgValue().indexOf(",") != -1) {
						String[] values = predicates.getArgValue().split(",");
						for (int j = 0; j < values.length; j++) {
							String value = values[j];
							predicate.getArgs().put(key + j, value);
						}
						
					} else {
						predicate.getArgs().put(key + 0, predicates.getArgValue());
					}
					
					routeDTO.getPredicates().add(predicate);
				}
			}
			
			routeDTO.setServiceId(route.getSetviceId());
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

	@Override
	public Integer addRoute(RouteDTO route) {
		RouteEntity routeEntity = convert(new RouteConverter()::apply, route);
		routeEntity = routeRepository.save(routeEntity);
		RedisConnection conn = redisConnectionFactory.getConnection();
		byte[] serializedAuth = null;
		try {
			serializedAuth = new ObjectMapper().writeValueAsBytes(routeEntity);
		} catch (JsonProcessingException e) {
		}
		byte[] authKey = "gateway:routes:add".getBytes();
		try {
			conn.openPipeline();
			conn.lPush(authKey, serializedAuth);
			conn.closePipeline();
		} finally {
			conn.close();
		}
		
		return routeEntity.getId();
	}

	@Override
	public Integer modifyRoute(RouteDTO route) {
		RouteEntity routeEntity = convert(new RouteConverter()::apply, route);
		routeEntity = routeRepository.save(routeEntity);
		RedisConnection conn = redisConnectionFactory.getConnection();
		byte[] serializedAuth = null;
		try {
			serializedAuth = new ObjectMapper().writeValueAsBytes(routeEntity);
		} catch (JsonProcessingException e) {
		}
		byte[] authKey = "gateway:routes:update".getBytes();
		try {
			conn.openPipeline();
			conn.lPush(authKey, serializedAuth);
			conn.closePipeline();
		} finally {
			conn.close();
		}
		return route.getId();
	}

	@Override
	public String removeRoute(String serviceId) {
		RouteEntity entity = new RouteEntity();
		entity.setSetviceId(serviceId);
		routeRepository.delete(entity);
		RedisConnection conn = redisConnectionFactory.getConnection();
		byte[] serializedAuth = serviceId.getBytes();
		byte[] authKey = "gateway:routes:delete".getBytes();
		try {
			conn.openPipeline();
			conn.lPush(authKey, serializedAuth);
			conn.closePipeline();
		} finally {
			conn.close();
		}
		
		return serviceId;
	}
}