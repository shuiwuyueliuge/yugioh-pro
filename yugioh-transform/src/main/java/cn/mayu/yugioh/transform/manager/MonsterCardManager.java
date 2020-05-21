package cn.mayu.yugioh.transform.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import cn.mayu.yugioh.common.core.domain.DomainConverterFactory;
import cn.mayu.yugioh.common.dto.cardsource.CardDetail;
import cn.mayu.yugioh.transform.domain.MonsterConverterFactory;
import cn.mayu.yugioh.transform.domain.dto.CardDTO;
import cn.mayu.yugioh.transform.domain.dto.CardTypeDTO;
import cn.mayu.yugioh.transform.domain.entity.MonsterEntity;
import cn.mayu.yugioh.transform.repository.MonsterRepository;
import cn.mayu.yugioh.transform.service.CardInfoService;
import cn.mayu.yugioh.transform.service.IndexService;

@Component
public class MonsterCardManager implements CardManager {
	
	@Autowired
	private MonsterRepository monsterRepository;
	
	@Autowired
	private CardInfoService cardInfoService; 
	
	private DomainConverterFactory<CardDetail, MonsterEntity> monsterConverterFactory;
	
	@Autowired
	public MonsterCardManager(IndexService indexService) {
		monsterConverterFactory = new MonsterConverterFactory(indexService);
	}

	@Override
	@Transactional
	public Integer cardSave(CardDTO cardDto) {
		CardTypeDTO cardTypeDto = cardDto.getCardTypeDTO();
		CardDetail card = cardDto.getCardDetail();
		MonsterEntity monster = monsterConverterFactory.convert(card);
		MonsterEntity monsterSaved = monsterRepository.findByNameAndPassword(card.getName(), card.getPassword());
		if (monsterSaved != null) monster.setId(monsterSaved.getId());
		monsterSaved = monsterRepository.save(monster);
		cardInfoService.saveAdjust(card.getAdjust(), monsterSaved.getId());
		cardInfoService.saveEffect(card, monsterSaved.getId());
		cardInfoService.saveLinks(card.getLinkArrow(), monsterSaved.getId());
		cardInfoService.saveTypes(cardTypeDto.getMonsterType(), monsterSaved.getId());
		return monsterSaved.getId();
	}
}