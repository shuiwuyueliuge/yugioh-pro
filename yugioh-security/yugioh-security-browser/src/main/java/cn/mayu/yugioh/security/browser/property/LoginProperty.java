package cn.mayu.yugioh.security.browser.property;

import org.springframework.boot.context.properties.ConfigurationProperties;
import lombok.Data;

@Data
@ConfigurationProperties(prefix = "yugioh.security.browser")
public class LoginProperty {

	private String loginPage;
	
	private String processingUrl;
	
	private String usernameParameter;
	
	private String passwordParameter;
}
