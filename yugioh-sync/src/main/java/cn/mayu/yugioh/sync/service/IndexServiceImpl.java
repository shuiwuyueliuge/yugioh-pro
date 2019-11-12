package cn.mayu.yugioh.sync.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.stereotype.Service;
import cn.mayu.yugioh.sync.entity.IndexEntity;
import cn.mayu.yugioh.sync.repository.IndexRepository;

@Service
public class IndexServiceImpl implements IndexService {
	
	@Autowired
	private IndexRepository indexRepository;
	
	@Autowired
	private ReactiveRedisTemplate<String, IndexEntity> redisTemplate;

	@Override
	public void indexCache() {
		indexRepository.findAll().stream().forEach(entity -> {
			redisTemplate.opsForSet().add(indexCacheKey(entity.getType()), entity).subscribe();
		});
	}

	@Override
	public Integer findByNameFromCache(Integer type, String name) {
		IndexEntity res = takeOutCache(type).stream().filter(entity -> entity.getName().equals(name)).findFirst().get();
	    return Optional.ofNullable(res.getTypeIndex()).orElse(0);
	}

	@Override
	public String findByTypeIndexFromCache(Integer type, Integer index) {
		IndexEntity res = takeOutCache(type).stream().filter(entity -> entity.getTypeIndex().equals(index)).findFirst().get();
	    return Optional.ofNullable(res.getName()).orElse("");
	}
	
	private List<IndexEntity> takeOutCache(Integer type) {
		return redisTemplate.opsForSet().members(indexCacheKey(type)).collectList().block();
	}
	
	private String indexCacheKey(Integer type) {
		return String.format("index:type:%s", type);
	}
}
