package cn.mayu.org.yugioh.security.core.base.property;

import org.springframework.boot.context.properties.ConfigurationProperties;
import lombok.Data;

@Data
@ConfigurationProperties(prefix = "yugioh.security.browser")
public class LoginProperty {

	private String loginPage;
	
	private String processingUrl = "/login";
	
	private String usernameParameter = "username";
	
	private String passwordParameter = "password";
}
