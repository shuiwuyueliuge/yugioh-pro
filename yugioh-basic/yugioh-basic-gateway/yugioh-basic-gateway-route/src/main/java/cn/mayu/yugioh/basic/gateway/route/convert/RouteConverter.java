package cn.mayu.yugioh.basic.gateway.route.convert;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import cn.mayu.yugioh.basic.gateway.route.dto.FilterDTO;
import cn.mayu.yugioh.basic.gateway.route.dto.RouteDTO;
import cn.mayu.yugioh.basic.gateway.route.dto.RouteDTO.RouteDefinition;
import cn.mayu.yugioh.basic.gateway.route.entity.RouteEntity;
import cn.mayu.yugioh.basic.gateway.route.service.DictService;

public class RouteConverter implements Converter<RouteDTO, RouteEntity> {
	
	private PredicatesConverter predicatesConverter;
	
	public RouteConverter(DictService dictService) {
		this.predicatesConverter = new PredicatesConverter(dictService);
	}

	@Override
	public RouteEntity apply(RouteDTO route) {
		RouteDefinition source = route.getRouteDefinition();
		RouteEntity routeEntity = new RouteEntity();
		routeEntity.setUri(source.getUri());
		routeEntity.setId(source.getTableId());
		routeEntity.setServiceId(source.getId());
		routeEntity.setSort(source.getOrder());
		routeEntity.setPredicatesEntities(predicatesConverter.applyList(source.getPredicates()));
		return routeEntity;
	}

	@Override
	public RouteDTO reverse(RouteEntity route) {
		FilterDTO filter = new FilterDTO();
		filter.setName("StripPrefix");
		filter.setArgs(new LinkedHashMap<String, String>());
		filter.getArgs().put("_genkey_0", "1");
		
		RouteDefinition definition = new RouteDefinition();
		definition.setFilters(new ArrayList<FilterDTO>());
		definition.getFilters().add(filter);
		definition.setPredicates(predicatesConverter.reverseList(route.getPredicatesEntities()));
		definition.setId(route.getServiceId());
		definition.setOrder(route.getSort());
		definition.setUri(route.getUri());
		
		RouteDTO routeDTO = new RouteDTO();
		routeDTO.setRouteDefinition(definition);
		return routeDTO;
	}
}
