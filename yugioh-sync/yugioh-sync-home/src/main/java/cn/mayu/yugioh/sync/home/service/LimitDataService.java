package cn.mayu.yugioh.sync.home.service;

import cn.mayu.yugioh.facade.sync.home.LimitProto;

public interface LimitDataService {

	void persistent(LimitProto.LimitEntity limitEntity) throws Exception;
}
