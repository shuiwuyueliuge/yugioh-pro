package cn.mayu.yugioh.security.browser.property;

import org.springframework.boot.context.properties.ConfigurationProperties;
import lombok.Data;

@Data
@ConfigurationProperties(prefix = "yugioh.security.browser")
public class CsrfProperty {
	
	private boolean csrfTokenRepositoryWithHttpOnlyFalse = false;
	
	private boolean csrfDisable = true;
	
	private String csrfIgnoringAnt;//, split
}
