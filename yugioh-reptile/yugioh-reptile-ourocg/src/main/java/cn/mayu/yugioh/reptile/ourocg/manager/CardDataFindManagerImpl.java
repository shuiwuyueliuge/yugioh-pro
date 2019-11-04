package cn.mayu.yugioh.reptile.ourocg.manager;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import cn.mayu.yugioh.common.core.html.HtmlHandler;
import cn.mayu.yugioh.common.mongo.entity.LimitEntity;
import cn.mayu.yugioh.reptile.ourocg.model.CardDetil;

@Component
public class CardDataFindManagerImpl implements CardDataFindManager {
	
	@Autowired
	private HtmlHandler<String> cardDataTranslater;
	
	@Autowired
	private HtmlHandler<CardDetil> includeInfoTranslater;
	
	@Autowired
	private HtmlHandler<List<LimitEntity>> limitDataTranslater;

	@Override
	public String findCardData(String url) throws Exception {
		return cardDataTranslater.handle(url);
	}
	
	@Override
	public CardDetil findIncludeInfo(String url) throws Exception {
		return includeInfoTranslater.handle(url);
	}

	@Override
	public List<LimitEntity> findLimitData(String latestUrl) throws Exception {
		return limitDataTranslater.handle(latestUrl);
	}
}