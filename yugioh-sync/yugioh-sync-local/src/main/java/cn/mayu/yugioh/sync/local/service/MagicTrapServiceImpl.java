package cn.mayu.yugioh.sync.local.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.mayu.yugioh.common.core.domain.DomainConverterFactory;
import cn.mayu.yugioh.common.mongo.entity.CardDataEntity;
import cn.mayu.yugioh.sync.local.entity.MagicTrapEntity;
import cn.mayu.yugioh.sync.local.repository.MagicTrapRepository;

@Service
public class MagicTrapServiceImpl implements MagicTrapService {
	
	@Autowired
	private DomainConverterFactory<CardDataEntity, MagicTrapEntity> mtConverterFactory;
	
	@Autowired
	private MagicTrapRepository magicTrapRepository;

	@Override
	@Transactional
	public void saveMagicTrapInfo(CardDataEntity entity) {
		MagicTrapEntity magicTrap = mtConverterFactory.convert(entity);
		Integer cardId = magicTrapRepository.save(magicTrap).getId();
		entity.setId(cardId);
	}
}
