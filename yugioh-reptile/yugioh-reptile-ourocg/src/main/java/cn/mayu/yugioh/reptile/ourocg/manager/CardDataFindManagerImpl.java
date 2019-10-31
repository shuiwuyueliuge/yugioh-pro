package cn.mayu.yugioh.reptile.ourocg.manager;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import cn.mayu.yugioh.common.core.html.HtmlHandler;
import cn.mayu.yugioh.common.mongo.entity.IncludeInfo;
import cn.mayu.yugioh.common.mongo.entity.LimitEntity;

@Component
public class CardDataFindManagerImpl implements CardDataFindManager {
	
	@Autowired
	private HtmlHandler<String> cardDataTranslater;
	
	@Autowired
	private HtmlHandler<List<IncludeInfo>> includeInfoTranslater;
	
	@Autowired
	private HtmlHandler<List<LimitEntity>> limitDataTranslater;

	@Override
	public String findCardData(String url) throws Exception {
		return cardDataTranslater.handle(url);
	}
	
	@Override
	public List<IncludeInfo> findIncludeInfo(String url) throws Exception {
		return includeInfoTranslater.handle(url);
	}

	@Override
	public List<LimitEntity> findLimitData(String latestUrl) throws Exception {
		return limitDataTranslater.handle(latestUrl);
	}
}