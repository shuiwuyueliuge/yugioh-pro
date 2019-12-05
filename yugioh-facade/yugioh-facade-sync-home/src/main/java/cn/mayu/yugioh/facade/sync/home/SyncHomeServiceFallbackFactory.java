package cn.mayu.yugioh.facade.sync.home;

import cn.mayu.yugioh.facade.sync.home.CardProto.CardEntity;
import cn.mayu.yugioh.facade.sync.home.LimitProto.LimitEntity;
import cn.mayu.yugioh.facade.sync.home.SaveResultProto.SaveResultEntity;

public class SyncHomeServiceFallbackFactory implements SyncHomeService {

	@Override
	public SaveResultEntity saveCardInMongo(CardEntity cardEntity) {
		return SaveResultEntity.getDefaultInstance().toBuilder().setCode(404).setMsg("err").build();
	}

	@Override
	public SaveResultEntity saveLimitInMongo(LimitEntity limitEntity) {
		return SaveResultEntity.getDefaultInstance().toBuilder().setCode(404).setMsg("err").build();
	}
}
