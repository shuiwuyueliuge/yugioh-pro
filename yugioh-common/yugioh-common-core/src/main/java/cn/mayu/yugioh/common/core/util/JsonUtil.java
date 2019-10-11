package cn.mayu.yugioh.common.core.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;

public class JsonUtil {

	private static final ObjectMapper MAPPER = new ObjectMapper();
	
	static {
		MAPPER.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
		MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	} 
	
	public static <T> T readValue(String content, Class<T> valueType) throws Exception {
		return MAPPER.readValue(content, valueType);
	}
	
	public static String readTree(String content, String value) throws Exception {
		return MAPPER.readTree(content).findParent(value).toString();
	}
	
	public <T> T readValue(String content, T type) throws Exception {
		return MAPPER.readValue(content, new TypeReference<T>() {});
	}
}