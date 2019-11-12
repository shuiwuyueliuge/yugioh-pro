package cn.mayu.yugioh.common.redis;

import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.serializer.RedisSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RedisConfigContext<K, V> {
	
	private RedisSerializer<K> keySerializer;
	
	private RedisSerializer<V> valueSerializer;
	
	private ReactiveRedisConnectionFactory factory;
}
