package cn.mayu.yugioh.sync.local.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.stereotype.Service;

import cn.mayu.yugioh.sync.local.entity.IndexEntity;
import cn.mayu.yugioh.sync.local.repository.IndexRepository;

@Service
public class IndexServiceImpl implements IndexService {

	@Autowired
	private IndexRepository indexRepository;

	@Autowired
	private ReactiveRedisTemplate<String, IndexEntity> redisTemplate;

	@Override
	public void indexCache() {
		indexRepository.findAll().stream().forEach(entity -> 
			redisTemplate.opsForSet().add(indexCacheKey(entity.getType()), entity).subscribe()
		);
	}

	@Override
	public Integer findByNameFromCache(Integer type, String name) {
		for (IndexEntity entity : takeOutCache(type)) {
			if (!entity.getName().equals(name)) {
				continue;
			}
			
			return entity.getTypeIndex();
		}
		
		return 0;
	}

	@Override
	public String findByTypeIndexFromCache(Integer type, Integer index) {
		for (IndexEntity entity : takeOutCache(type)) {
			if (!entity.getTypeIndex().equals(index)) {
				continue;
			}
			
			return entity.getName();
		}
		
		return "";
	}

	private List<IndexEntity> takeOutCache(Integer type) {
		return redisTemplate.opsForSet().members(indexCacheKey(type)).collectList().block();
	}

	private String indexCacheKey(Integer type) {
		return String.format("index:type:%s", type);
	}
}
