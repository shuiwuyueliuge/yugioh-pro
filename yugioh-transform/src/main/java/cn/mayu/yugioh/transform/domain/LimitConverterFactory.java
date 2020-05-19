package cn.mayu.yugioh.transform.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import cn.mayu.yugioh.common.core.domain.DomainConverterFactory;
import cn.mayu.yugioh.common.dto.cardsource.LimitDetail;
import cn.mayu.yugioh.transform.domain.entity.ForbiddenEntity;

public class LimitConverterFactory implements DomainConverterFactory<LimitDetail, ForbiddenEntity> {

	@Override
	public List<ForbiddenEntity> convertList(LimitDetail limitDetail) {
		List<ForbiddenEntity> entities = new ArrayList<ForbiddenEntity>();
		entities.addAll(limitDetail.getForbidden().stream().map(detail -> {
			ForbiddenEntity forbiddenEntity = new ForbiddenEntity();
			forbiddenEntity.setCardName(detail);
			forbiddenEntity.setLimitVal(0);
			forbiddenEntity.setLimitTime(limitDetail.getName());
			return forbiddenEntity;
		}).collect(Collectors.toList()));
		entities.addAll(limitDetail.getLimited().stream().map(detail -> {
			ForbiddenEntity forbiddenEntity = new ForbiddenEntity();
			forbiddenEntity.setCardName(detail);
			forbiddenEntity.setLimitTime(limitDetail.getName());
			forbiddenEntity.setLimitVal(1);
			return forbiddenEntity;
		}).collect(Collectors.toList()));
		entities.addAll(limitDetail.getSemiLimited().stream().map(detail -> {
			ForbiddenEntity forbiddenEntity = new ForbiddenEntity();
			forbiddenEntity.setCardName(detail);
			forbiddenEntity.setLimitTime(limitDetail.getName());
			forbiddenEntity.setLimitVal(2);
			return forbiddenEntity;
		}).collect(Collectors.toList()));
		return entities;
	}
}
