package cn.mayu.yugioh.basic.gateway.route.convert;

import java.util.stream.Collectors;
import org.springframework.beans.BeanUtils;
import cn.mayu.yugioh.basic.gateway.route.dto.FilterDTO;
import cn.mayu.yugioh.basic.gateway.route.dto.PredicateDTO;
import cn.mayu.yugioh.basic.gateway.route.dto.RouteDTO;
import cn.mayu.yugioh.basic.gateway.route.dto.RouteDTO.RouteDefinition;
import cn.mayu.yugioh.basic.gateway.route.entity.PredicatesEntity;
import cn.mayu.yugioh.basic.gateway.route.entity.RouteEntity;
import cn.mayu.yugioh.basic.gateway.route.service.DictService;

public class RouteConverter implements Converter<RouteDTO, RouteEntity> {
	
	private DictService dictService;
	
	private PredicatesConverter predicatesConverter;
	
	public RouteConverter(DictService dictService) {
		this.dictService = dictService;
		this.predicatesConverter = new PredicatesConverter(dictService);
	}

	@Override
	public RouteEntity apply(RouteDTO route) {
		RouteDefinition source = route.getRouteDefinition();
		RouteEntity routeEntity = new RouteEntity();
		BeanUtils.copyProperties(source, routeEntity);
		if (route.getRouteDefinition().getId() != null) {
			routeEntity.setId(Integer.parseInt(route.getRouteDefinition().getId()));
		}
		
		routeEntity.setSort(source.getOrder());
		routeEntity.setPredicatesEntities(source.getPredicates().stream().map(predicates -> {
			return predicatesConverter.apply(predicates);
		}).collect(Collectors.toList()));
		return routeEntity;
	}

	@Override
	public RouteDTO reverse(RouteEntity route) {
		FilterDTO filter = new FilterDTO();
		filter.setName("StripPrefix");
		filter.getArgs().put("_genkey_0", "1");
		
		RouteDTO routeDTO = new RouteDTO();
		RouteDefinition definition = new RouteDefinition();
		routeDTO.setRouteDefinition(definition);
		routeDTO.getRouteDefinition().getFilters().add(filter);
		
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
				
				routeDTO.getRouteDefinition().getPredicates().add(predicate);
			}
		}
		
		routeDTO.getRouteDefinition().setId(route.getServiceId());
		routeDTO.getRouteDefinition().setOrder(route.getSort());
		routeDTO.getRouteDefinition().setUri(route.getUri());
		return routeDTO;
	}
}
