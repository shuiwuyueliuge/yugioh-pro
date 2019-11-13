package cn.mayu.yugioh.sync.service;

import cn.mayu.yugioh.common.mongo.entity.CardDataEntity;

public interface MagicTrapService {

	Integer saveMagicTrapInfo(CardDataEntity entity);
}
