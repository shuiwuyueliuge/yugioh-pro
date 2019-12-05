package cn.mayu.yugioh.sync.local.service;

import cn.mayu.yugioh.facade.sync.home.CardProto.CardEntity;

public interface CardService {

	void saveCardData(CardEntity entity);
	
	void updateCardData(CardEntity entity);
}
