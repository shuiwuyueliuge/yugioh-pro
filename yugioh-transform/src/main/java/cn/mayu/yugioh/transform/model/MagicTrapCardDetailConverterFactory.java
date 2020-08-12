package cn.mayu.yugioh.transform.model;

import cn.mayu.yugioh.common.core.domain.DomainConverterFactory;
import cn.mayu.yugioh.common.core.util.BeanUtil;
import cn.mayu.yugioh.common.dto.transform.CardDetail;
import cn.mayu.yugioh.transform.model.entity.MagicTrapEntity;
import com.google.common.collect.Lists;
import org.springframework.beans.BeanUtils;

public class MagicTrapCardDetailConverterFactory implements DomainConverterFactory<MagicTrapEntity, CardDetail> {

	@Override
	public CardDetail convert(MagicTrapEntity source) {
		CardDetail cardDetail = new CardDetail();
		BeanUtil.copyProperties(source, cardDetail);
		cardDetail.setTypeSt(Lists.newArrayList(source.getTypeSt().toString()));
		cardDetail.setTypeVal(source.getTypeVal().toString());
		return cardDetail;
	}
}
