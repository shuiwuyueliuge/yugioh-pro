package cn.mayu.yugioh.sync.local.service;

import cn.mayu.yugioh.facade.sync.home.CardProto.CardEntity;
import cn.mayu.yugioh.sync.local.entity.MonsterEntity;

public interface MonsterService {

	void saveMonsterInfo(CardEntity entity);
	
	void updateMonsterInfo(CardEntity entity);
	
	MonsterEntity findByNameAndPassword(String name, String password);
}
