package cn.mayu.yugioh.basic.gateway.route.convert;

import java.util.LinkedHashMap;
import java.util.List;
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
		PredicateDTO predicate = new PredicateDTO();
		String key = "_genkey_";
		predicate.setArgs(new LinkedHashMap<String, String>());
		predicate.setName(dictService.getValue(1, target.getArgName()));
		if (target.getArgValue().indexOf(",") != -1) {
			String[] values = target.getArgValue().split(",");
			for (int j = 0; j < values.length; j++) {
				String value = values[j];
				predicate.getArgs().put(key + j, value);
			}
			
			return predicate;
		}
		
		predicate.getArgs().put(key + 0, target.getArgValue());
		return predicate;
	}

	@Override
	public PredicatesEntity apply(PredicateDTO source) {
		PredicatesEntity entity =new PredicatesEntity();
		entity.setArgName(dictService.getName(1, source.getName()));
		String args = source.getArgs().values().stream().collect(Collectors.joining(","));
		entity.setArgValue(args);
		return entity;
	}
	
	@Override
	public List<PredicatesEntity> applyList(List<PredicateDTO> source) {
		return source.stream().map(this::apply).collect(Collectors.toList());
	}
	
	@Override
	public List<PredicateDTO> reverseList(List<PredicatesEntity> target) {
		if (target == null) return null;
		return target.stream().map(this::reverse).collect(Collectors.toList());
	}
}
