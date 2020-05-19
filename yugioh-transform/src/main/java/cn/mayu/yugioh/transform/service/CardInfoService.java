package cn.mayu.yugioh.transform.service;

import java.util.List;
import cn.mayu.yugioh.common.dto.cardsource.CardDetail;

public interface CardInfoService {

	void saveAdjust(String adjust, Integer cardId);
	
	void saveEffect(CardDetail card, Integer cardId);
	
	void saveLinks(List<String> linkArrow, Integer cardId);
	
	void saveTypes(List<Integer> types, Integer cardId);
}
