package cn.mayu.yugioh.sync.local.service;

import cn.mayu.yugioh.common.mongo.entity.CardDataEntity;

public interface MonsterService {

	void saveMonsterInfo(CardDataEntity entity);
}
