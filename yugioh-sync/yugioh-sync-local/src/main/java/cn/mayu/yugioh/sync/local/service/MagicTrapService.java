package cn.mayu.yugioh.sync.local.service;

import cn.mayu.yugioh.facade.sync.home.CardProto.CardEntity;
import cn.mayu.yugioh.sync.local.entity.MagicTrapEntity;

public interface MagicTrapService {

	void saveMagicTrapInfo(CardEntity entity);
	
	void updateMagicTrapInfo(CardEntity entity);
	
	MagicTrapEntity findByNameAndPassword(String name, String password);
}
