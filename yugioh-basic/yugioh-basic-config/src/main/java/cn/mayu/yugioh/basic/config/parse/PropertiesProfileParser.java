package cn.mayu.yugioh.basic.config.parse;

import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

public class PropertiesProfileParser implements ProfileParser {

	@Override
	public Map<Object, Object> parse(InputStream stream) throws Exception {
		Properties properties = new Properties();
		properties.load(stream);
		return properties;
	}
}
