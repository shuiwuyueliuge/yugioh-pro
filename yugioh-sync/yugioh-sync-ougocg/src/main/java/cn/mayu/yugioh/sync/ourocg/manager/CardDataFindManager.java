package cn.mayu.yugioh.sync.ourocg.manager;

import java.util.List;
import cn.mayu.yugioh.common.dto.sync.home.LimitProto;
import cn.mayu.yugioh.sync.ourocg.model.CardDetil;

public interface CardDataFindManager {

	String findCardData(String url) throws Exception;
	
	CardDetil findIncludeInfo(String url) throws Exception;

	List<LimitProto.LimitEntity> findLimitData(String latestUrl) throws Exception;
}
