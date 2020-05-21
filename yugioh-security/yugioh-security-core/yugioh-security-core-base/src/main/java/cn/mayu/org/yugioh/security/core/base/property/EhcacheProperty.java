package cn.mayu.org.yugioh.security.core.base.property;

import org.springframework.boot.context.properties.ConfigurationProperties;
import lombok.Data;

@Data
@ConfigurationProperties(prefix = "yugioh.security.encache")
public class EhcacheProperty {

	private long heapSize = 1000L;
	
	private long offHeapSize = 100L;
	
	private long timeToIdleSecond = 30L;
	
	private long timeToLiveSecond = 30L;
}
