package cn.mayu.yugioh.transform.service;

import java.util.List;
import cn.mayu.yugioh.common.dto.transform.CardDetail;
import cn.mayu.yugioh.common.dto.transform.CardProto;

public interface CardInfoService {

	void saveAdjust(String adjust, Integer cardId, Integer cardType);
	
	void saveEffect(CardProto.CardDetail card, Integer cardId, Integer cardType);
	
	void saveLinks(List<String> linkArrow, Integer cardId);
	
	void saveTypes(List<Integer> types, Integer cardId);

	List<CardDetail> findByIdAndTypeVal(List<CardDetail> details);
}
