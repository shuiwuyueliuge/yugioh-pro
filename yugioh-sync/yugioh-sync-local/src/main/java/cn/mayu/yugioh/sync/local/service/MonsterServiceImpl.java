package cn.mayu.yugioh.sync.local.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import cn.mayu.yugioh.common.core.domain.DomainConverterFactory;
import cn.mayu.yugioh.common.dto.sync.home.CardProto.CardEntity;
import cn.mayu.yugioh.sync.local.config.CardIdThreadLocal;
import cn.mayu.yugioh.sync.local.entity.LinkEntity;
import cn.mayu.yugioh.sync.local.entity.MonsterEntity;
import cn.mayu.yugioh.sync.local.entity.TypeEntity;
import cn.mayu.yugioh.sync.local.repository.LinkRepository;
import cn.mayu.yugioh.sync.local.repository.MonsterRepository;
import cn.mayu.yugioh.sync.local.repository.TypeRepository;

@Service
public class MonsterServiceImpl implements MonsterService {
	
	@Autowired
	private MonsterRepository monsterRepository;
	
	@Autowired
	private LinkRepository linkRepository;
	
	@Autowired
	private TypeRepository typeRepository;
	
	@Autowired
	private CardIdThreadLocal threadLocal;
	
	@Autowired
	private DomainConverterFactory<CardEntity, MonsterEntity> monsterConverterFactory;
	
	@Autowired
	private DomainConverterFactory<CardEntity, List<TypeEntity>> typeConverterFactory;
	
	@Autowired
	private DomainConverterFactory<CardEntity, List<LinkEntity>> linkConverterFactory;

	@Override
	@Transactional
	public void saveMonsterInfo(CardEntity entity) {	
		MonsterEntity monster = monsterConverterFactory.convert(entity);
		MonsterEntity saved = monsterRepository.save(monster);
		threadLocal.setId(saved.getId());
		List<TypeEntity> monsterTypes = typeConverterFactory.convert(entity);
		typeRepository.saveAll(monsterTypes);
		List<LinkEntity> links = linkConverterFactory.convert(entity);
		linkRepository.saveAll(links);
	}

	@Override
	@Transactional
	public void updateMonsterInfo(CardEntity entity) {
		MonsterEntity saved = monsterRepository.findByNameAndPassword(entity.getName(), entity.getPassword());
		MonsterEntity monster = monsterConverterFactory.convert(entity);
		monster.setId(saved.getId());
		saved = monsterRepository.save(monster);
		threadLocal.setId(saved.getId());
		List<TypeEntity> monsterTypes = typeConverterFactory.convert(entity);
		typeRepository.saveAll(monsterTypes);
		List<LinkEntity> links = linkConverterFactory.convert(entity);
		linkRepository.saveAll(links);
	}

	@Override
	public MonsterEntity findByNameAndPassword(String name, String password) {
		return monsterRepository.findByNameAndPassword(name, password);
	}
}
