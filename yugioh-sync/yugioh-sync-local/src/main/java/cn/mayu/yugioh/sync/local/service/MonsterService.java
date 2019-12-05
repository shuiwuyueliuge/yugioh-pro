package cn.mayu.yugioh.sync.local.service;

import cn.mayu.yugioh.facade.sync.home.CardProto.CardEntity;

public interface MonsterService {

	void saveMonsterInfo(CardEntity entity);
	
	void updateMonsterInfo(CardEntity entity);
}
