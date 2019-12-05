package cn.mayu.yugioh.sync.local.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.mayu.yugioh.common.core.domain.DomainConverterFactory;
import cn.mayu.yugioh.facade.sync.home.CardProto.CardEntity;
import cn.mayu.yugioh.sync.local.config.CardIdThreadLocal;
import cn.mayu.yugioh.sync.local.entity.MagicTrapEntity;
import cn.mayu.yugioh.sync.local.repository.MagicTrapRepository;

@Service
public class MagicTrapServiceImpl implements MagicTrapService {
	
	@Autowired
	private DomainConverterFactory<CardEntity, MagicTrapEntity> mtConverterFactory;
	
	@Autowired
	private MagicTrapRepository magicTrapRepository;
	
	@Autowired
	private CardIdThreadLocal threadLocal;

	@Override
	@Transactional
	public void saveMagicTrapInfo(CardEntity entity) {
		MagicTrapEntity magicTrap = mtConverterFactory.convert(entity);
		Integer cardId = magicTrapRepository.save(magicTrap).getId();
		threadLocal.setId(cardId);
	}

	@Override
	@Transactional
	public void updateMagicTrapInfo(CardEntity entity) {
		MagicTrapEntity saved = magicTrapRepository.findByNameAndPassword(entity.getName(), entity.getPassword());
		MagicTrapEntity magicTrap = mtConverterFactory.convert(entity);
		magicTrap.setId(saved.getId());
		Integer cardId = magicTrapRepository.save(magicTrap).getId();
		threadLocal.setId(cardId);
	}
}
