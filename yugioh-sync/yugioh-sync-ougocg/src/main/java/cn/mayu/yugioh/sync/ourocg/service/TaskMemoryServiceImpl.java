package cn.mayu.yugioh.sync.ourocg.service;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class TaskMemoryServiceImpl implements TaskMemoryService {

	@Autowired
	private ReactiveRedisTemplate<String, Long> redisTemplate;
	
	private static final Duration TIMEOUT = Duration.ofDays(1L); 

	@Override
	public void markMemory(String key, Long value) {
		redisTemplate.opsForValue().set(key, value, TIMEOUT).subscribe();
	}

	@Override
	public Long checkMemory(String key) {
		Long value = redisTemplate.opsForValue().get(key).block();
		return value == null ? 0L : value;
	}

	@Override
	public void increaseBy(String key) {
		redisTemplate.opsForValue().increment(key).subscribe();
	}

	@Override
	public void remove(String key) {
		redisTemplate.delete(key).subscribe();
	}
}
