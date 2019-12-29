package cn.mayu.yugioh.security.core.social;

import org.springframework.boot.context.properties.ConfigurationProperties;
import lombok.Data;

@Data
@ConfigurationProperties(prefix = "yugioh.security.social")
public class SocialProperty {
	
	private String signupUrl = "/index.html";
	
	private String tablePrefix = "";
}
