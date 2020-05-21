package cn.mayu.yugioh.security.browser.property;

import java.util.Properties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertiesPropertySource;

public class BrowserEnvironmentPostProcessor implements EnvironmentPostProcessor {

	@Override
	public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
		Properties properties = new Properties();
		String timeout = environment.getProperty("yugioh.security.browser.session-timeout");
		String type = environment.getProperty("yugioh.security.browser.sessionStore-type");
		if (timeout == null)  timeout = "1800";
		if (type == null)  type = "none";
		properties.put("server.servlet.session.timeout", timeout);
		properties.put("spring.session.store-type", type);
		PropertiesPropertySource propertySource = new PropertiesPropertySource("session", properties);
		environment.getPropertySources().addLast(propertySource);
	}
}
