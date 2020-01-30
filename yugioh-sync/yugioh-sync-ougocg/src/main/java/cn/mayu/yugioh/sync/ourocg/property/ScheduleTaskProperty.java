package cn.mayu.yugioh.sync.ourocg.property;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;
import lombok.Data;

@Data
@RefreshScope
@Configuration
@ConfigurationProperties(prefix = "yugioh.sync.ourocg.crawing")
public class ScheduleTaskProperty {

	private String corn;
	
	private boolean enabled;
}
