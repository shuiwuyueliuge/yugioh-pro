package cn.mayu.yugioh.basic.gateway.route.service;

import cn.mayu.yugioh.basic.gateway.route.dto.RouteDTO;

public interface RouteService {

	void initRoute();

	Integer addRoute(RouteDTO route);

	Integer modifyRoute(RouteDTO route);

	String removeRoute(String serviceId);
	
}
