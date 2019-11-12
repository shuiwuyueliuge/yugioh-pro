package cn.mayu.yugioh.sync.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import cn.mayu.yugioh.common.redis.RedisConfigContext;
import cn.mayu.yugioh.common.redis.RedisConfiguration;
import cn.mayu.yugioh.sync.entity.IndexEntity;

@Configuration
public class RedisConfig {
	
	@Autowired
	private ReactiveRedisConnectionFactory factory;

	@Bean
	public ReactiveRedisTemplate<String, IndexEntity> IndexEntityRedis() {
		RedisConfigContext<String, IndexEntity> context = new RedisConfigContext<String, IndexEntity>(new StringRedisSerializer(), new Jackson2JsonRedisSerializer<IndexEntity>(IndexEntity.class), factory);
		RedisConfiguration<String, IndexEntity> configuration = new RedisConfiguration<String, IndexEntity>(context);
		return configuration.reactiveRedisTemplate();
	}
}
