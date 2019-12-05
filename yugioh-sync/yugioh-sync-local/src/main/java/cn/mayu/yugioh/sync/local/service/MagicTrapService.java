package cn.mayu.yugioh.sync.local.service;

import cn.mayu.yugioh.facade.sync.home.CardProto.CardEntity;

public interface MagicTrapService {

	void saveMagicTrapInfo(CardEntity entity);
	
	void updateMagicTrapInfo(CardEntity entity);
}
