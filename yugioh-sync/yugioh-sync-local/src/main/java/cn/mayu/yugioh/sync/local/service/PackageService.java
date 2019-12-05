package cn.mayu.yugioh.sync.local.service;

import cn.mayu.yugioh.facade.sync.home.CardProto.CardEntity;

public interface PackageService {
 
	void savePackageInfo(CardEntity entity);
}
