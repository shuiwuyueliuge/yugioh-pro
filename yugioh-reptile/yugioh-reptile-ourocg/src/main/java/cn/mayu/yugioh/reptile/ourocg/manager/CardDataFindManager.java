package cn.mayu.yugioh.reptile.ourocg.manager;

import java.util.List;
import cn.mayu.yugioh.common.mongo.entity.LimitEntity;
import cn.mayu.yugioh.reptile.ourocg.model.CardDetil;

public interface CardDataFindManager {

	String findCardData(String url) throws Exception;
	
	CardDetil findIncludeInfo(String url) throws Exception;

	List<LimitEntity> findLimitData(String latestUrl) throws Exception;
}
