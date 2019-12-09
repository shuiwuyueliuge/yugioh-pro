package cn.mayu.yugioh.common.redis;

import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializationContext.RedisSerializationContextBuilder;

public class YugiohRedisFactory {

	public static <K, V> ReactiveRedisTemplate<K, V> reactiveRedisTemplate(RedisConfigContext<K, V> context) {
		RedisSerializationContextBuilder<K, V> build = RedisSerializationContext.newSerializationContext();
		RedisSerializationContext<K, V> ctx = build.key(context.getKeySerializer())
				                                   .value(context.getValueSerializer())
				                                   .hashKey(context.getKeySerializer())
				                                   .hashValue(context.getValueSerializer())
				                                   .build();
		return new ReactiveRedisTemplate<K, V>(context.getFactory(), ctx);
	}
}
