package cn.mayu.yugioh.transform.manager;

import cn.mayu.yugioh.common.dto.transform.CardDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import cn.mayu.yugioh.transform.model.dto.CardDTO;

@Component
public class CardManagerContext {
	
	@Autowired
	private MagicTrapCardManager magicTrapCardManager;
	
	@Autowired
	private MonsterCardManager monsterCardManager;

	public Integer cardSave(CardDTO cardDto) {
		return getCardManager(cardDto.getCardTypeDTO().getCardType()).cardSave(cardDto);
	}

	public CardDetail findByIdAndTypeVal(Integer id, Integer typeVal) {
		return getCardManager(typeVal).findByIdAndTypeVal(id);
	}

	private CardManager getCardManager(Integer cardType) {
		return cardType == 1 ? monsterCardManager : magicTrapCardManager;
	}
}
