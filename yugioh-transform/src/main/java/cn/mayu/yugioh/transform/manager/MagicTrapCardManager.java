package cn.mayu.yugioh.transform.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import cn.mayu.yugioh.common.dto.cardsource.CardDetail;
import cn.mayu.yugioh.transform.domain.dto.CardDTO;
import cn.mayu.yugioh.transform.domain.dto.CardTypeDTO;
import cn.mayu.yugioh.transform.domain.entity.MagicTrapEntity;
import cn.mayu.yugioh.transform.repository.MagicTrapRepository;
import cn.mayu.yugioh.transform.service.CardInfoService;

@Component
public class MagicTrapCardManager implements CardManager {
	
	@Autowired
	private MagicTrapRepository magicTrapRepository;
	
	@Autowired
	private CardInfoService cardInfoService; 

	@Override
	@Transactional
	public Integer cardSave(CardDTO cardDto) {
		CardTypeDTO cardTypeDto = cardDto.getCardTypeDTO();
		CardDetail card = cardDto.getCardDetail();
		MagicTrapEntity magicTrap = magicTrapRepository.findByNameAndPassword(card.getName(), card.getPassword());
		if (magicTrap == null) magicTrap = new MagicTrapEntity();
		magicTrap.setPassword(card.getPassword());
		magicTrap.setName(card.getName());
		magicTrap.setNameJa(card.getNameJa());
		magicTrap.setNameEn(card.getNameEn());
		magicTrap.setNameNw(card.getNameNw());
		magicTrap.setTypeSt(cardTypeDto.getMagicTrapType());
		magicTrap.setTypeVal(cardTypeDto.getCardType());
		magicTrap = magicTrapRepository.save(magicTrap);
		cardInfoService.saveAdjust(card.getAdjust(), magicTrap.getId());
		cardInfoService.saveEffect(card, magicTrap.getId());
		return magicTrap.getId();
	}
}