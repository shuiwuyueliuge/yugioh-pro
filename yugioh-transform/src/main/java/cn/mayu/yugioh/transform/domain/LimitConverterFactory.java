package cn.mayu.yugioh.transform.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import cn.mayu.yugioh.common.core.domain.DomainConverterFactory;
import cn.mayu.yugioh.common.core.util.DateUtil;
import cn.mayu.yugioh.common.dto.cardsource.LimitProto;
import cn.mayu.yugioh.transform.domain.entity.ForbiddenEntity;

public class LimitConverterFactory implements DomainConverterFactory<LimitProto.LimitDetail, ForbiddenEntity> {

	@Override
	public List<ForbiddenEntity> convertList(LimitProto.LimitDetail limitDetail) {
		List<ForbiddenEntity> entities = new ArrayList<ForbiddenEntity>();
		entities.addAll(limitDetail.getForbiddenList().stream().map(detail -> {
			ForbiddenEntity forbiddenEntity = new ForbiddenEntity();
			forbiddenEntity.setCardName(detail);
			forbiddenEntity.setLimitVal(0);
			forbiddenEntity.setRegion(limitDetail.getRegion());
			forbiddenEntity.setLimitTime(DateUtil.toLocalDate(limitDetail.getPublishTime()));
			return forbiddenEntity;
		}).collect(Collectors.toList()));
		entities.addAll(limitDetail.getLimitedList().stream().map(detail -> {
			ForbiddenEntity forbiddenEntity = new ForbiddenEntity();
			forbiddenEntity.setCardName(detail);
			forbiddenEntity.setRegion(limitDetail.getRegion());
			forbiddenEntity.setLimitTime(DateUtil.toLocalDate(limitDetail.getPublishTime()));
			forbiddenEntity.setLimitVal(1);
			return forbiddenEntity;
		}).collect(Collectors.toList()));
		entities.addAll(limitDetail.getSemiLimitedList().stream().map(detail -> {
			ForbiddenEntity forbiddenEntity = new ForbiddenEntity();
			forbiddenEntity.setCardName(detail);
			forbiddenEntity.setRegion(limitDetail.getRegion());
			forbiddenEntity.setLimitTime(DateUtil.toLocalDate(limitDetail.getPublishTime()));
			forbiddenEntity.setLimitVal(2);
			return forbiddenEntity;
		}).collect(Collectors.toList()));
		return entities;
	}
}
