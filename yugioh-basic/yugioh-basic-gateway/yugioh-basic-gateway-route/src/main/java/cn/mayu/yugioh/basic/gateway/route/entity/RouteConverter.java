package cn.mayu.yugioh.basic.gateway.route.entity;

import org.springframework.beans.BeanUtils;
import cn.mayu.yugioh.basic.gateway.route.dto.RouteDTO;

public class RouteConverter implements Converter<RouteDTO, RouteEntity> {

	@Override
	public RouteEntity apply(RouteDTO source) {
		RouteEntity routeEntity = new RouteEntity();
		BeanUtils.copyProperties(source, routeEntity);
		return routeEntity;
	}
}
