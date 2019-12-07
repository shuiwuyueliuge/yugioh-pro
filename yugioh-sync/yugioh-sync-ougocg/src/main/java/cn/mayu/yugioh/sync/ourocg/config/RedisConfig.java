package cn.mayu.yugioh.sync.ourocg.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import cn.mayu.yugioh.common.redis.YugiohRedisFactory;

@Configuration
public class RedisConfig {

	@Bean
	public ReactiveRedisTemplate<String, String> IndexEntityRedis(ReactiveRedisConnectionFactory factory) {
		return YugiohRedisFactory.reactiveRedisTemplateString(factory);
	}
}
