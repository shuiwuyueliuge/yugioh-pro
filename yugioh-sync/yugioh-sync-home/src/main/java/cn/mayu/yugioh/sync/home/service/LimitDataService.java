package cn.mayu.yugioh.sync.home.service;

import cn.mayu.yugioh.common.dto.sync.home.LimitProto;

public interface LimitDataService {

	void persistent(LimitProto.LimitEntity limitEntity);
}
