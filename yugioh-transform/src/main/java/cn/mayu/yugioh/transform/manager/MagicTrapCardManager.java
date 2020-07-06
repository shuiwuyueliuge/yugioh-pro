package cn.mayu.yugioh.transform.manager;

import cn.mayu.yugioh.common.dto.transform.CardDetail;
import cn.mayu.yugioh.common.dto.transform.CardProto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import cn.mayu.yugioh.transform.model.dto.CardDTO;
import cn.mayu.yugioh.transform.model.dto.CardTypeDTO;
import cn.mayu.yugioh.transform.model.entity.MagicTrapEntity;
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
		CardProto.CardDetail card = cardDto.getCardDetail();
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
		cardInfoService.saveAdjust(card.getAdjust(), magicTrap.getId(), cardTypeDto.getCardType());
		cardInfoService.saveEffect(card, magicTrap.getId(), cardTypeDto.getCardType());
		return magicTrap.getId();
	}

	@Override
	public CardDetail findByIdAndTypeVal(Integer id) {
		return null;
	}
}
