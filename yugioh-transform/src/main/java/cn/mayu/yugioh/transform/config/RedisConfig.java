package cn.mayu.yugioh.transform.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import cn.mayu.yugioh.common.redis.RedisFactory;
import cn.mayu.yugioh.transform.model.entity.IndexEntity;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

@Configuration
public class RedisConfig {
	
	@Autowired
	private ReactiveRedisConnectionFactory factory;

	@Bean
	public ReactiveRedisTemplate<String, IndexEntity> IndexEntityRedis() {
		return RedisFactory.initStringRedisTemplate(factory, new Jackson2JsonRedisSerializer<>(IndexEntity.class));
	}
}
