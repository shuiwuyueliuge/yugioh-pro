package cn.mayu.yugioh.security.core.sms;

import org.springframework.boot.context.properties.ConfigurationProperties;
import lombok.Data;

@Data
@ConfigurationProperties(prefix = "yugioh.security.validate-code.sms")
public class SmsValidateCodeProperty {
	
	private String processingUrl = "/login/sms";
}
