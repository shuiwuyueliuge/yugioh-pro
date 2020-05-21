package cn.mayu.yugioh.common.core.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;

public class JsonUtil {
	
	public static <T> T readValue(String content, Class<T> valueType) throws Exception {
		return init().readValue(content, valueType);
	}
	
	public static <T> T readValueSnakeCase(String content, Class<T> valueType) throws Exception {
		ObjectMapper mapper = init();
		mapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
		return mapper.readValue(content, valueType);
	}
	
	public static <T> T readValue(byte[] content, Class<T> valueType) throws Exception {
		return init().readValue(content, valueType);
	}
	
	public static Long readTree(String content, String value) throws Exception {
		return init().readTree(content).findValue(value).asLong();
	}
	
	public <T> T readValue(String content, T type) throws Exception {
		return init().readValue(content, new TypeReference<T>() {});
	}
	
	public static byte[] writeValueAsBytes(Object value) throws Exception {
		return init().writeValueAsBytes(value);
	}
	
	public static String writeValueAsString(Object value) throws Exception {
		return init().writeValueAsString(value);
	}
	
	private static ObjectMapper init() {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		return mapper;
	}
}