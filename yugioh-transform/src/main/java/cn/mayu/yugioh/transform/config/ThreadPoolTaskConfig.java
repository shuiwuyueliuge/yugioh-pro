package cn.mayu.yugioh.transform.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;
import lombok.Data;

@ConfigurationProperties(prefix = "async.pool")
@Data
@Configuration
@RefreshScope
public class ThreadPoolTaskConfig {
	
	private int coreSize;
	
	private int maxSize;
	
	private int queueSize;
	
	private String threadNamePrefix;
}
