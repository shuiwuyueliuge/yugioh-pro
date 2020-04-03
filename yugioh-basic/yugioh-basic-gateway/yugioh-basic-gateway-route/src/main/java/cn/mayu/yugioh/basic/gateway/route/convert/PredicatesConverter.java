package cn.mayu.yugioh.basic.gateway.route.convert;

import java.util.stream.Collectors;
import cn.mayu.yugioh.basic.gateway.route.dto.PredicateDTO;
import cn.mayu.yugioh.basic.gateway.route.entity.PredicatesEntity;
import cn.mayu.yugioh.basic.gateway.route.service.DictService;

public class PredicatesConverter implements Converter<PredicateDTO, PredicatesEntity> {
	
	private DictService dictService;
	
	public PredicatesConverter(DictService dictService) {
		this.dictService = dictService;
	}

	@Override
	public PredicateDTO reverse(PredicatesEntity target) {
		return null;
	}

	@Override
	public PredicatesEntity apply(PredicateDTO source) {
		PredicatesEntity entity =new PredicatesEntity();
		entity.setArgName(dictService.getName(1, source.getName()));
		String args = source.getArgs().values().stream().collect(Collectors.joining(","));
		entity.setArgValue(args);
		return entity;
	}
}
