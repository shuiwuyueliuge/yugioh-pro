package cn.mayu.yugioh.sync.home.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import lombok.Data;

@ConfigurationProperties(prefix = "async.pool")
@Data
@Configuration
public class ThreadPoolTaskConfig {
	
	private int coreSize;
	
	private int maxSize;
	
	private int queueSize;
	
	private String threadNamePrefix;
}
