package cn.mayu.yugioh.sync.ourocg.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class TaskMemoryServiceImpl implements TaskMemoryService {

	@Autowired
	private ReactiveRedisTemplate<String, String> redisTemplate;

	@Override
	public void markMemory(String key, String value) {
		redisTemplate.opsForValue().set(key, value).subscribe();
	}

	@Override
	public String checkMemory(String key) {
		return redisTemplate.opsForValue().get(key).block();
	}

	@Override
	public void increaseBy(String key) {
		redisTemplate.opsForValue().increment(key).subscribe();
	}
}
