package cn.mayu.org.yugioh.security.core.base.validatecode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DefaultValidateCodeProcessorHolder implements ValidateCodeProcessorHolder {
	
	private Map<String, ValidateCodeProcessor> map;
	
	{
		map = new HashMap<String, ValidateCodeProcessor>();
	}

	public DefaultValidateCodeProcessorHolder(Set<ValidateCodeProcessor> set) {
		set.forEach(processor -> {
			map.put(processor.getType(), processor);
		});
		
		log.debug("init validate code type: [{}]", Arrays.toString(set.toArray()));
	}

	@Override
	public ValidateCodeProcessor getProcessor(String type) {
		return map.get(type);
	}

	@Override
	public Set<String> getValidateCodeKey() {
		return map.values().stream().map(processor -> processor.getValidateCodeKey()).collect(Collectors.toSet());
	}

	@Override
	public Set<String> getValidateCode() {
		return map.values().stream().map(processor -> processor.getValidateCode()).collect(Collectors.toSet());
	}
}
