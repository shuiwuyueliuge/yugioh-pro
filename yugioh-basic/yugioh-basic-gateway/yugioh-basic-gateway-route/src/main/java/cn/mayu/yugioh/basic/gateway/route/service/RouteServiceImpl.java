package cn.mayu.yugioh.basic.gateway.route.service;

import static cn.mayu.yugioh.basic.gateway.route.convert.BeanConvertFactory.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.mayu.yugioh.basic.gateway.route.convert.RouteConverter;
import cn.mayu.yugioh.basic.gateway.route.dto.RouteDTO;
import static cn.mayu.yugioh.basic.gateway.route.dto.RouteDTO.RouteStatus.*;
import cn.mayu.yugioh.basic.gateway.route.entity.RouteEntity;
import cn.mayu.yugioh.basic.gateway.route.repository.RouteRepository;
import cn.mayu.yugioh.common.core.util.JsonUtil;

@Service
public class RouteServiceImpl implements RouteService {
	
	@Autowired
	private RouteRepository routeRepository;
	
	@Autowired
	private RedisConnectionFactory redisConnectionFactory;
	
	private static final byte[] ROUTE_KEY = "gateway:routes".getBytes();
	
	private RouteConverter routeConverter;
	
	@Autowired
	public RouteServiceImpl(DictService dictService) {
		this.routeConverter = new RouteConverter(dictService);
	}
	
	@Override
	public void initRoute() {
		routeRepository.findByState(0).stream().forEach(route -> {
			RouteDTO routeDto = reverse(routeConverter, route);
			routeDto.setStatus(CREATE.getStatus());
			lPush(ROUTE_KEY, getRouteByte(routeDto));
		});
	}

	@Override
	@Transactional
	public Integer addRoute(RouteDTO route) {
		RouteEntity routeEntity = convert(routeConverter, route);
		routeEntity = routeRepository.save(routeEntity);
		route.setStatus(CREATE.getStatus());
		route.getRouteDefinition().setId(routeEntity.getServiceId());
		lPush(ROUTE_KEY, getRouteByte(route));
		return routeEntity.getId();
	}

	@Override
	@Transactional
	public Integer modifyRoute(RouteDTO route) {
		RouteEntity routeEntity = convert(routeConverter, route);
		routeEntity = routeRepository.save(routeEntity);
		route.setStatus(UPDATE.getStatus());
		lPush(ROUTE_KEY, getRouteByte(route));
		return routeEntity.getId();
	}

	@Override
	@Transactional
	public String removeRoute(String serviceId) {
		RouteEntity routeEntity = routeRepository.findByServiceId(serviceId);
		routeRepository.delete(routeEntity);
		RouteDTO route = reverse(routeConverter, routeEntity);
		route.setStatus(DELETE.getStatus());
		lPush(ROUTE_KEY, getRouteByte(route));
		return serviceId;
	}
	
	private byte[] getRouteByte(Object route) {
		try {
			return JsonUtil.writeValueAsBytes(route);
		} catch (Exception e) {
			throw new RuntimeException("route as bytes error", e);
		}
	}
	
	private Long lPush(byte[] key, byte[]... value) {
		RedisConnection conn = redisConnectionFactory.getConnection();
		try {
			conn.openPipeline();
			Long res = conn.lPush(key, value);
			conn.closePipeline();
			return res;
		} finally {
			conn.close();
		}
	}
}