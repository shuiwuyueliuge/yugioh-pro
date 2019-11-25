package cn.mayu.yugioh.sync.ourocg.config;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import cn.mayu.yugioh.sync.ourocg.model.OurocgCard;

public class OurocgCardDeserializer extends JsonDeserializer<OurocgCard> {

	@Override
	public OurocgCard deserialize(JsonParser jp, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {
		JsonNode jsonNode = jp.getCodec().readTree(jp);
		Class<OurocgCard> clazz = OurocgCard.class;
		OurocgCard card = null;
		try {
			card = clazz.newInstance();
		} catch (Exception e) {
		}

		Map<String, Method> map = new HashMap<String, Method>();
		for (Method method : clazz.getMethods()) {
			if (method.getName().indexOf("set") == -1) {
				continue;
			}
			
			map.put(method.getName(), method);
		}
		
		Iterator<Entry<String, JsonNode>> iterator = jsonNode.fields();
		while (iterator.hasNext()) {
			Entry<String, JsonNode> entity = iterator.next();
			String fieldName = entity.getKey();
			if (entity.getKey().indexOf("_") != -1) {
				String[] key = entity.getKey().split("_");
				StringBuilder builder = new StringBuilder();
				builder.append(key[0]);
				for (int i = 1; i < key.length; i++) {
					char start = (char) ((int) key[i].charAt(0) - 32);
					builder.append(start).append(key[i].substring(1, key[i].length()));
				}

				fieldName = builder.toString();
			}

			char start = (char) ((int) fieldName.charAt(0) - 32);
			String methodName = String.format("set%s%s", start, fieldName.substring(1, fieldName.length()));
			Method method = map.remove(methodName);
			if (method.getParameters()[0].getType() == String.class) {
				String value = entity.getValue().asText();
				try {
					method.invoke(card, value.equals("null") ? "" : value);
				} catch (Exception e) {
				}
			}

			if (method.getParameters()[0].getType() == Integer.class) {
				JsonNode valueNode = entity.getValue();
				int value = 0;
				if (valueNode != null) {
					value = entity.getValue().asInt();
				}

				try {
					method.invoke(card, value);
				} catch (Exception e) {
				}
			}
		}

		for(Method method : map.values()) {
			if (method.getParameters()[0].getType() == String.class) {
				try {
					method.invoke(card, "");
				} catch (Exception e) {
				}
			}

			if (method.getParameters()[0].getType() == Integer.class) {
				try {
					method.invoke(card, 0);
				} catch (Exception e) {
				}
			}
		}
		
		return card;
	}
}
