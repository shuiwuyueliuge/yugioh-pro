package cn.mayu.yugioh.sync.ourocg.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import cn.mayu.yugioh.common.core.util.DateUtil;
import cn.mayu.yugioh.common.redis.LongRedisSerializer;
import cn.mayu.yugioh.common.redis.RedisConfigContext;
import cn.mayu.yugioh.common.redis.YugiohRedisFactory;

@Configuration
public class RedisConfig {
	
	private static final String OUROCG_PAGE_KEY = "%s:ourocg:page";
	
	public static final String SKIP_SIZE_KEY = "%s:card:skip";

	@Bean
	public ReactiveRedisTemplate<String, Long> memoryRedis(ReactiveRedisConnectionFactory factory) {
		RedisConfigContext<String, Long> context = new RedisConfigContext<String, Long>(new StringRedisSerializer(),
				new LongRedisSerializer(), factory);
		return YugiohRedisFactory.reactiveRedisTemplate(context);
	}
	
	public static String todayOurocgPageKey() {
		return String.format(OUROCG_PAGE_KEY, DateUtil.dayNow());
	}
}
