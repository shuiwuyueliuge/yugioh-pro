package cn.mayu.yugioh.sync;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import cn.mayu.yugioh.sync.entity.IndexEntity;

@Configuration
public class Config {

	@Bean
	public RedisTemplate<String, IndexEntity> init(RedisConnectionFactory redisConnectionFactory) {
		 RedisTemplate<String, IndexEntity> template = new RedisTemplate<String, IndexEntity>();
	        template.setConnectionFactory(redisConnectionFactory);
	        template.setKeySerializer(new StringRedisSerializer());
	        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
	        return template;
	}
}
