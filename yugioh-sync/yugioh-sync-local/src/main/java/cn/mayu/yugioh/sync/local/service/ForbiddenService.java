package cn.mayu.yugioh.sync.local.service;

import cn.mayu.yugioh.facade.sync.home.LimitDetilProto.LimitDetilEntity;

public interface ForbiddenService {

	void saveLimitCard(LimitDetilEntity entity);
}
