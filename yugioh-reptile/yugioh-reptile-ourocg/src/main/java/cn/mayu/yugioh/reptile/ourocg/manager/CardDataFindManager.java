package cn.mayu.yugioh.reptile.ourocg.manager;

import java.util.List;
import cn.mayu.yugioh.common.mongo.entity.IncludeInfo;
import cn.mayu.yugioh.common.mongo.entity.LimitEntity;

public interface CardDataFindManager {

	String findMetaData(String url) throws Exception;
	
	List<IncludeInfo> findPackageData(String url) throws Exception;

	List<LimitEntity> findLimitCard(String latestUrl) throws Exception;
}
