package cn.mayu.yugioh.basic.gateway;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import lombok.Data;

@Data
@Configuration
@ConfigurationProperties(prefix = "config.oauth2")
public class RsaProperty {

	private String privateKey;
	
	private String publicKey;
}
