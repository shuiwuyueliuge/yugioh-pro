package cn.mayu.yugioh.transform.manager;

import cn.mayu.yugioh.common.dto.transform.CardDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import cn.mayu.yugioh.transform.model.dto.CardDTO;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CardManagerContext {
	
	@Autowired
	private MagicTrapCardManager magicTrapCardManager;
	
	@Autowired
	private MonsterCardManager monsterCardManager;

	public Integer cardSave(CardDTO cardDto) {
		return getCardManager(cardDto.getCardTypeDTO().getCardType()).cardSave(cardDto);
	}

	public List<CardDetail> findByIdAndTypeVal(List<CardDetail> details) {
		return details.stream().map(data -> getCardManager(Integer.valueOf(data.getTypeVal())).findByIdAndTypeVal(data)).collect(Collectors.toList());
	}

	private CardManager getCardManager(Integer cardType) {
		return cardType == 1 ? monsterCardManager : magicTrapCardManager;
	}
}
