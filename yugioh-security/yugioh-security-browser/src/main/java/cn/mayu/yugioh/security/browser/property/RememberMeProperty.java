package cn.mayu.yugioh.security.browser.property;

import org.springframework.boot.context.properties.ConfigurationProperties;
import lombok.Data;

@Data
@ConfigurationProperties(prefix = "yugioh.security.browser")
public class RememberMeProperty {

	private int tokenValiditySeconds = 1000 * 60 * 30;
}
