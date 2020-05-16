package cn.mayu.yugioh.common.core.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {

	private static final ObjectMapper MAPPER = new ObjectMapper();
	
	static {
		MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	} 
	
	public static <T> T readValue(String content, Class<T> valueType) throws Exception {
		return MAPPER.readValue(content, valueType);
	}
	
	public static <T> T readValue(byte[] content, Class<T> valueType) throws Exception {
		return MAPPER.readValue(content, valueType);
	}
	
	public static Long readTree(String content, String value) throws Exception {
		return MAPPER.readTree(content).findValue(value).asLong();
	}
	
	public <T> T readValue(String content, T type) throws Exception {
		return MAPPER.readValue(content, new TypeReference<T>() {});
	}
	
	public static byte[] writeValueAsBytes(Object value) throws Exception {
		return MAPPER.writeValueAsBytes(value);
	}
	
	public static String writeValueAsString(Object value) throws Exception {
		return MAPPER.writeValueAsString(value);
	}
}