package cn.mayu.yugioh.sync.service;

import cn.mayu.yugioh.common.mongo.entity.CardDataEntity;

public interface MagicTrapService {

	void saveMagicTrapInfo(CardDataEntity entity);
}
