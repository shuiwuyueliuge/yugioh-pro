package cn.mayu.yugioh.reptile.ourocg.manager;

import java.util.List;
import cn.mayu.yugioh.common.mongo.entity.IncludeInfo;
import cn.mayu.yugioh.common.mongo.entity.LimitEntity;

public interface CardDataFindManager {

	String findCardData(String url) throws Exception;
	
	List<IncludeInfo> findIncludeInfo(String url) throws Exception;

	List<LimitEntity> findLimitData(String latestUrl) throws Exception;
}
