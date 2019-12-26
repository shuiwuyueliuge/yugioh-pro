package cn.mayu.org.yugioh.security.core.base.property;

import org.springframework.boot.context.properties.ConfigurationProperties;
import lombok.Data;

@Data
@ConfigurationProperties(prefix = "yugioh.security.validate-code")
public class ValidateCodeLoginProperty {

	private String codeValueParam = "code";
	
	private String keyParam = "key";
}
