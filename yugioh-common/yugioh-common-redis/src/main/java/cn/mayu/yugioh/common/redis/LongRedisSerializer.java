package cn.mayu.yugioh.common.redis;

import java.nio.charset.Charset;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;
import org.springframework.lang.Nullable;

public class LongRedisSerializer implements RedisSerializer<Long> {
	
	private final Charset charset = Charset.forName("UTF-8");

	@Override
	public byte[] serialize(@Nullable Long t) throws SerializationException {
		String str = t.toString();
		return (str == null ? null : str.getBytes(charset));
	}

	@Override
	public Long deserialize(@Nullable byte[] bytes) throws SerializationException {
		String str = (bytes == null ? null : new String(bytes, charset));
		return Long.valueOf(str);
	}
}
