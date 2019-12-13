package cn.mayu.yugioh.sync.local.service;

import cn.mayu.yugioh.common.dto.sync.home.LimitDetilProto.LimitDetilEntity;

public interface ForbiddenService {

	void saveLimitCard(LimitDetilEntity entity);
}
