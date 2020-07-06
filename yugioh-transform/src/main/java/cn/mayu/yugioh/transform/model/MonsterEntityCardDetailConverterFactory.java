package cn.mayu.yugioh.transform.model;

import cn.mayu.yugioh.common.core.domain.DomainConverterFactory;
import cn.mayu.yugioh.common.core.util.BeanUtil;
import cn.mayu.yugioh.common.dto.transform.CardDetail;
import cn.mayu.yugioh.transform.model.entity.MonsterEntity;

public class MonsterEntityCardDetailConverterFactory implements DomainConverterFactory<MonsterEntity, CardDetail> {

	@Override
	public CardDetail convert(MonsterEntity source) {
		CardDetail cardDetail = new CardDetail();
		BeanUtil.copyProperties(source, cardDetail);
		return cardDetail;
	}
}
