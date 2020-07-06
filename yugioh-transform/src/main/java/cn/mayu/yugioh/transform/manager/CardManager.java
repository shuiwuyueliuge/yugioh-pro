package cn.mayu.yugioh.transform.manager;

import cn.mayu.yugioh.common.dto.transform.CardDetail;
import cn.mayu.yugioh.transform.model.dto.CardDTO;

public interface CardManager {

	Integer cardSave(CardDTO cardDto);

	CardDetail findByIdAndTypeVal(Integer id);
}
