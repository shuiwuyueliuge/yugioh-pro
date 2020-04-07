package cn.mayu.yugioh.basic.gateway.route.convert;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import cn.mayu.yugioh.basic.gateway.route.dto.FilterDTO;
import cn.mayu.yugioh.basic.gateway.route.entity.FilterArgEntity;
import cn.mayu.yugioh.basic.gateway.route.entity.FilterEntity;
import cn.mayu.yugioh.basic.gateway.route.service.DictService;

public class FilterConverter implements Converter<FilterDTO, FilterEntity> {

	private DictService dictService;

	public FilterConverter(DictService dictService) {
		this.dictService = dictService;
	}

	@Override
	public FilterDTO reverse(FilterEntity target) {
		FilterDTO filterDto = new FilterDTO();
		filterDto.setName(dictService.getValue(2, target.getFilterName()));
		Map<String, String> map = new LinkedHashMap<String, String>();
		target.getFilterArgEntities().stream().forEach(data -> {
			if (data.getArgName() != null) {
				map.put(data.getArgName(), data.getArgValue());
			} else {
				String key = "_genkey_";
				if (data.getArgValue().indexOf(",") != -1) {
					String[] values = data.getArgValue().split(",");
					for (int i = 0; i < values.length; i++) {
						map.put(key + i, values[i]);
					}
				} else {
					map.put(key + 0, data.getArgValue());
				}
			}
		});
		
		filterDto.setArgs(map);
		return filterDto;
	}

	@Override
	public FilterEntity apply(FilterDTO source) {
		FilterEntity filterEntity = new FilterEntity();
		filterEntity.setFilterName(dictService.getName(2, source.getName()));
		filterEntity.setFilterArgEntities(source.getArgs().entrySet().stream().map(data -> {
			FilterArgEntity argEntity = new FilterArgEntity();
			argEntity.setArgName(data.getKey().indexOf("_genkey_") != -1 ? null : data.getKey());
			argEntity.setArgValue(data.getValue());
			return argEntity;
		}).collect(Collectors.toList()));
		return filterEntity;
	}

	@Override
	public List<FilterEntity> applyList(List<FilterDTO> source) {
		return source.stream().map(this::apply).collect(Collectors.toList());
	}

	@Override
	public List<FilterDTO> reverseList(List<FilterEntity> target) {
		if (target == null)
			return null;
		return target.stream().map(this::reverse).collect(Collectors.toList());
	}
}
