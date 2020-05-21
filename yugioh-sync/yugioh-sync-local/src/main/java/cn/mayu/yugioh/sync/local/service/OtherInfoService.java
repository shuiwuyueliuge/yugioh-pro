package cn.mayu.yugioh.sync.local.service;

import cn.mayu.yugioh.common.dto.sync.home.CardProto.CardEntity;

public interface OtherInfoService {

	void saveOtherData(CardEntity entity);
}
