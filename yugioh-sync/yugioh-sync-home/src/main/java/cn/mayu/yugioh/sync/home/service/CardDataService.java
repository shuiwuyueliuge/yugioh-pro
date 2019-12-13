package cn.mayu.yugioh.sync.home.service;

import cn.mayu.yugioh.common.dto.sync.home.CardProto.CardEntity;

public interface CardDataService {

	void persistent(CardEntity cardEntity) throws Exception;
}
