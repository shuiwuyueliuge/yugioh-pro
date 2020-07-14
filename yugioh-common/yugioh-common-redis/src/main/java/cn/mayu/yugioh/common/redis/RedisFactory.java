package cn.mayu.yugioh.common.redis;

import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializationContext.RedisSerializationContextBuilder;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

public class RedisFactory {

	public static <K, V> ReactiveRedisTemplate<K, V> reactiveRedisTemplate(RedisConfigContext<K, V> context) {
		RedisSerializationContextBuilder<K, V> build = RedisSerializationContext.newSerializationContext();
		RedisSerializationContext<K, V> ctx = build.key(context.getKeySerializer())
				                                   .value(context.getValueSerializer())
				                                   .hashKey(context.getKeySerializer())
				                                   .hashValue(context.getValueSerializer())
				                                   .build();
		return new ReactiveRedisTemplate<K, V>(context.getFactory(), ctx);
	}

	public static <String, V> ReactiveRedisTemplate<java.lang.String, V> initStringRedisTemplate(ReactiveRedisConnectionFactory factory, RedisSerializer<V> valueSerializer) {
		RedisSerializer<java.lang.String> keySerializer = new StringRedisSerializer();
		RedisSerializationContextBuilder<java.lang.String, V> build = RedisSerializationContext.newSerializationContext();
		RedisSerializationContext<java.lang.String, V> ctx = build.key(keySerializer)
				.value(valueSerializer)
				.hashKey(keySerializer)
				.hashValue(valueSerializer)
				.build();
		return new ReactiveRedisTemplate<>(factory, ctx);
	}

	public static <Integer, V> ReactiveRedisTemplate<java.lang.Integer, V> initIntegerRedisTemplate(ReactiveRedisConnectionFactory factory, RedisSerializer<V> valueSerializer) {
		RedisSerializer<java.lang.Integer> keySerializer = new IntRedisSerializer();
		RedisSerializationContextBuilder<java.lang.Integer, V> build = RedisSerializationContext.newSerializationContext();
		RedisSerializationContext<java.lang.Integer, V> ctx = build.key(keySerializer)
				.value(valueSerializer)
				.hashKey(keySerializer)
				.hashValue(valueSerializer)
				.build();
		return new ReactiveRedisTemplate<>(factory, ctx);
	}
}
