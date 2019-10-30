package cn.mayu.yugioh.reptile.ourocg.manager;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import cn.mayu.yugioh.common.core.html.HtmlTranslater;
import cn.mayu.yugioh.common.mongo.entity.IncludeInfo;
import cn.mayu.yugioh.common.mongo.entity.LimitEntity;

@Component
public class CardDataFindManagerImpl implements CardDataFindManager {
	
	@Autowired
	private HtmlTranslater<String> cardDataTranslater;
	
	@Autowired
	private HtmlTranslater<List<IncludeInfo>> includeInfoTranslater;
	
	@Autowired
	private HtmlTranslater<List<LimitEntity>> limitDataTranslater;

	@Override
	public String findCardData(String url) throws Exception {
		return cardDataTranslater.visitHtml(url);
	}
	
	@Override
	public List<IncludeInfo> findIncludeInfo(String url) throws Exception {
		return includeInfoTranslater.visitHtml(url);
	}

	@Override
	public List<LimitEntity> findLimitData(String latestUrl) throws Exception {
		return limitDataTranslater.visitHtml(latestUrl);
	}
}