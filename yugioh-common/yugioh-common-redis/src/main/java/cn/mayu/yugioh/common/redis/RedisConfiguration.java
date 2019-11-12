package cn.mayu.yugioh.common.redis;

import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializationContext.RedisSerializationContextBuilder;

public class RedisConfiguration<K, V> {

	private RedisConfigContext<K, V> context;
	
	public RedisConfiguration(RedisConfigContext<K, V> context) {
		this.context = context;
	}
	
	public ReactiveRedisTemplate<K, V> reactiveRedisTemplate() {
		RedisSerializationContextBuilder<K, V> build = RedisSerializationContext.newSerializationContext();
		RedisSerializationContext<K, V> ctx = build.key(context.getKeySerializer())
				                                   .value(context.getValueSerializer())
				                                   .hashKey(context.getKeySerializer())
				                                   .hashValue(context.getValueSerializer())
				                                   .build();
		return new ReactiveRedisTemplate<K, V>(context.getFactory(), ctx);
	}
}
