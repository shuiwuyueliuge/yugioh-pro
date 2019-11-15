package cn.mayu.yugioh.sync.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import cn.mayu.yugioh.common.core.domain.DomainConverterFactory;
import cn.mayu.yugioh.common.mongo.entity.CardDataEntity;
import cn.mayu.yugioh.sync.entity.LinkEntity;
import cn.mayu.yugioh.sync.entity.MonsterEntity;
import cn.mayu.yugioh.sync.entity.TypeEntity;
import cn.mayu.yugioh.sync.repository.LinkRepository;
import cn.mayu.yugioh.sync.repository.MonsterRepository;
import cn.mayu.yugioh.sync.repository.TypeRepository;

@Service
public class MonsterServiceImpl implements MonsterService {
	
	@Autowired
	private MonsterRepository monsterRepository;
	
	@Autowired
	private LinkRepository linkRepository;
	
	@Autowired
	private TypeRepository typeRepository;
	
	@Autowired
	private DomainConverterFactory<CardDataEntity, MonsterEntity> monsterConverterFactory;
	
	@Autowired
	private DomainConverterFactory<CardDataEntity, List<TypeEntity>> typeConverterFactory;
	
	@Autowired
	private DomainConverterFactory<CardDataEntity, List<LinkEntity>> linkConverterFactory;

	@Override
	@Transactional
	public void saveMonsterInfo(CardDataEntity entity) {
		MonsterEntity monster = monsterConverterFactory.convert(entity);
		MonsterEntity saved = monsterRepository.save(monster);
		entity.setId(saved.getId());
		List<TypeEntity> monsterTypes = typeConverterFactory.convert(entity);
		typeRepository.saveAll(monsterTypes);
		List<LinkEntity> links = linkConverterFactory.convert(entity);
		linkRepository.saveAll(links);
	}
}
