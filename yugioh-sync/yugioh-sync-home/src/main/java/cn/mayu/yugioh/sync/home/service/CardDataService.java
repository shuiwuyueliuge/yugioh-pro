package cn.mayu.yugioh.sync.home.service;

import cn.mayu.yugioh.facade.sync.home.CardProto.CardEntity;

public interface CardDataService {

	void persistent(CardEntity cardEntity) throws Exception;
}
