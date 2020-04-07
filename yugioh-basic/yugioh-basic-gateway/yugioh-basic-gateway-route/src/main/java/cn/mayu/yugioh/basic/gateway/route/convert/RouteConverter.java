package cn.mayu.yugioh.basic.gateway.route.convert;

import cn.mayu.yugioh.basic.gateway.route.dto.RouteDTO;
import cn.mayu.yugioh.basic.gateway.route.dto.RouteDTO.RouteDefinition;
import cn.mayu.yugioh.basic.gateway.route.entity.RouteEntity;
import cn.mayu.yugioh.basic.gateway.route.service.DictService;

public class RouteConverter implements Converter<RouteDTO, RouteEntity> {
	
	private PredicatesConverter predicatesConverter;
	
	private FilterConverter filterConverter;
	
	public RouteConverter(DictService dictService) {
		this.predicatesConverter = new PredicatesConverter(dictService);
		this.filterConverter = new FilterConverter(dictService);
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
		routeEntity.setFilterEntities(filterConverter.applyList(source.getFilters()));
		return routeEntity;
	}

	@Override
	public RouteDTO reverse(RouteEntity route) {
		RouteDefinition definition = new RouteDefinition();
		definition.setFilters(filterConverter.reverseList(route.getFilterEntities()));
		definition.setPredicates(predicatesConverter.reverseList(route.getPredicatesEntities()));
		definition.setId(route.getServiceId());
		definition.setOrder(route.getSort());
		definition.setUri(route.getUri());
		RouteDTO routeDTO = new RouteDTO();
		routeDTO.setRouteDefinition(definition);
		return routeDTO;
	}
}
