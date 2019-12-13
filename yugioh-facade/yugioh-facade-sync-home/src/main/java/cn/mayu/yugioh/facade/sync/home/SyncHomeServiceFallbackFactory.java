package cn.mayu.yugioh.facade.sync.home;

import static cn.mayu.yugioh.common.core.api.ResultCodeEnum.FAILURE;
import cn.mayu.yugioh.common.dto.sync.home.ResultProtoFactory;
import cn.mayu.yugioh.common.dto.sync.home.CardProto.CardEntity;
import cn.mayu.yugioh.common.dto.sync.home.LimitProto.LimitEntity;
import cn.mayu.yugioh.common.dto.sync.home.ResultProto.ResultEntity;

public class SyncHomeServiceFallbackFactory implements SyncHomeService {

	@Override
	public ResultEntity saveCardInMongo(CardEntity cardEntity) {
		return new ResultProtoFactory().createResultModel(FAILURE);
	}

	@Override
	public ResultEntity saveLimitInMongo(LimitEntity limitEntity) {
		return new ResultProtoFactory().createResultModel(FAILURE);
	}
}
