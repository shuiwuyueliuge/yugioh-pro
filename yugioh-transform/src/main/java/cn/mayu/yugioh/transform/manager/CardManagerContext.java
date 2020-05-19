package cn.mayu.yugioh.transform.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import cn.mayu.yugioh.transform.domain.dto.CardDTO;

@Component
public class CardManagerContext {
	
	@Autowired
	private MagicTrapCardManager magicTrapCardManager;
	
	@Autowired
	private MonsterCardManager monsterCardManager;

	public Integer cardSave(CardDTO cardDto) {
		CardManager manager = cardDto.getCardTypeDTO().getCardType() == 1 ? monsterCardManager : magicTrapCardManager;
		return manager.cardSave(cardDto);
	}
}
