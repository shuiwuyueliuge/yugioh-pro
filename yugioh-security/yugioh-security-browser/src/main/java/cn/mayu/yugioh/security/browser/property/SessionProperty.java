package cn.mayu.yugioh.security.browser.property;

import org.springframework.boot.context.properties.ConfigurationProperties;
import lombok.Data;

@Data
@ConfigurationProperties(prefix = "yugioh.security.browser")
public class SessionProperty {
	
	private int maximumSessions = 1;
	
	private boolean maxSessionsPreventsLogin = false;
	
	private int sessionTimeout = 1800;
	
	private String sessionStoreType = "none";
}

//server.servlet.session.timeout=1

//spring.session.store-type=none
