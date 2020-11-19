package cn.mayu.yugioh.security.core.base.social;

import org.springframework.boot.context.properties.ConfigurationProperties;
import lombok.Data;

@Data
@ConfigurationProperties(prefix = "yugioh.security.social")
public class SocialProperty {
	
	private String signUpUrl = "/index.html";
	
	private String tablePrefix = "";
}
