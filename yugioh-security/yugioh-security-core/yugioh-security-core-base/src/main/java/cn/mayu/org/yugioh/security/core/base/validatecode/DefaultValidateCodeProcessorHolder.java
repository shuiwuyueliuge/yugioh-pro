package cn.mayu.org.yugioh.security.core.base.validatecode;

import java.util.Arrays;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DefaultValidateCodeProcessorHolder implements ValidateCodeProcessorHolder {
	
	private Map<String, ValidateCodeProcessor> map;

	public DefaultValidateCodeProcessorHolder(Map<String, ValidateCodeProcessor> map) {
		this.map = map;
		log.debug("init validate code type: [{}]", Arrays.toString(map.keySet().toArray()));
	}

	@Override
	public ValidateCodeProcessor getProcessor(String type) {
		return map.get(type);
	}
}
