package cn.mayu.yugioh.cardsource.core.ourocg;

import cn.mayu.yugioh.cardsource.core.ourocg.model.OurocgCard;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.slf4j.Slf4j;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

@Slf4j
public class OurocgCardDeserializer extends JsonDeserializer<OurocgCard> {

	@Override
	public OurocgCard deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		Map<String, Method> methodMap = initMap(OurocgCard.class);
		JsonNode jsonNode = jp.getCodec().readTree(jp);
		return build(methodMap, jsonNode);
	}
	
	private OurocgCard build(Map<String, Method> map, JsonNode jsonNode) {
		OurocgCard card = new OurocgCard();
		Iterator<Entry<String, JsonNode>> iterator = jsonNode.fields();
		while (iterator.hasNext()) {
			Entry<String, JsonNode> entity = iterator.next();
			String methodName = getMethodName(entity.getKey());
			Method method = map.remove(methodName);
			Class<?> parameterClass = method.getParameters()[0].getType();
			invoke(method, card, entity.getValue(), parameterClass);
		}

		dealWithMap(map, card);
		return card;
	} 
	
	private String getMethodName(String fieldName) {
		if (fieldName.indexOf("_") != -1) {
			String[] key = fieldName.split("_");
			StringBuilder builder = new StringBuilder();
			builder.append(key[0]);
			for (int i = 1; i < key.length; i++) {
				char start = (char) ((int) key[i].charAt(0) - 32);
				builder.append(start).append(key[i].substring(1, key[i].length()));
			}

			fieldName = builder.toString();
		}

		char start = (char) ((int) fieldName.charAt(0) - 32);
		return String.format("set%s%s", start, fieldName.substring(1, fieldName.length()));
	}
	
	private Map<String, Method> initMap(Class<OurocgCard> clazz) {
		Map<String, Method> map = new HashMap<String, Method>();
		for (Method method : clazz.getMethods()) {
			if (method.getName().indexOf("set") == -1) {
				continue;
			}
			
			map.put(method.getName(), method);
		}
		
		return map;
	}
	
	private void invoke(Method method, OurocgCard card, JsonNode node, Class<?> parameterClass) {
		Object arg = null;
		if (node != null) {
			if (parameterClass == String.class) {
				String value = node.asText().trim();
				arg = value.equals("null") ? "" : value;
			}
			
			if (parameterClass == Integer.class) {
				arg = node.asInt();
			}
		}
		
		if (node == null) {
			if (parameterClass == String.class) {
				arg = "";
			}
			
			if (parameterClass == Integer.class) {
				arg = 0;
			}
		}
		
		try {
			method.invoke(card, arg);
		} catch (Exception e) {
			log.error("invoke method: [{}] arg: [{}] error: [{}]", method.getName(), arg, e);
		}
	}
	
	private void dealWithMap(Map<String, Method> map, OurocgCard card) {
		map.values().stream().forEach(method -> {
			Class<?> parameterClass = method.getParameters()[0].getType();
			invoke(method, card, null, parameterClass);
		});
	}
}
