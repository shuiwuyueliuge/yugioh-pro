package cn.mayu.yugioh.common.redis;

import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;
import org.springframework.lang.Nullable;

import java.nio.charset.Charset;

public class IntRedisSerializer implements RedisSerializer<Integer> {
	
	private final Charset charset = Charset.forName("UTF-8");

	@Override
	public byte[] serialize(@Nullable Integer t) throws SerializationException {
		String str = t.toString();
		return (str == null ? null : str.getBytes(charset));
	}

	@Override
	public Integer deserialize(@Nullable byte[] bytes) throws SerializationException {
		String str = (bytes == null ? null : new String(bytes, charset));
		return Integer.valueOf(str);
	}
}
