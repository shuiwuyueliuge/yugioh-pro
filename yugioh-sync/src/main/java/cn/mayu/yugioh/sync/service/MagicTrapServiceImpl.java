package cn.mayu.yugioh.sync.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.mayu.yugioh.common.core.domain.DomainConverterFactory;
import cn.mayu.yugioh.common.mongo.entity.CardDataEntity;
import cn.mayu.yugioh.sync.entity.MagicTrapEntity;
import cn.mayu.yugioh.sync.repository.MagicTrapRepository;

@Service
public class MagicTrapServiceImpl implements MagicTrapService {
	
	@Autowired
	private DomainConverterFactory<CardDataEntity, MagicTrapEntity> mtConverterFactory;
	
	@Autowired
	private MagicTrapRepository magicTrapRepository;

	@Override
	public Integer saveMagicTrapInfo(CardDataEntity entity) {
		MagicTrapEntity magicTrap = mtConverterFactory.convert(entity);
		return magicTrapRepository.save(magicTrap).getId();
	}
}
