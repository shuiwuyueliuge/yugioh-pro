package cn.mayu.yugioh.reptile.ourocg.manager;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import cn.mayu.yugioh.common.core.html.HtmlTranslater;
import cn.mayu.yugioh.common.mongo.entity.CardDataEntity.IncludeInfo;
import cn.mayu.yugioh.common.mongo.entity.CardLimitEntity;
import static cn.mayu.yugioh.common.core.util.CrawlUtil.*;

@Component
public class CardDataFindManagerImpl implements CardDataFindManager {
	
	@Autowired
	private HtmlTranslater<String> cardDataTranslater;
	
	@Autowired
	private HtmlTranslater<List<IncludeInfo>> includeInfoTranslater;

	@Override
	public String findMetaData(String url) throws Exception {
		return cardDataTranslater.visit(url);
	}
	
	@Override
	public List<IncludeInfo> findPackageData(String url) throws Exception {
		return includeInfoTranslater.visit(url);
	}

	@Override
	public List<CardLimitEntity> findLimitCard(String latestUrl) throws Exception {
		return searchUrlList(latestUrl).stream().map(url -> {
			try {
				Map<String, List<String>> map = getLimitHashId(url);
				CardLimitEntity dto = new CardLimitEntity();
				dto.setName(map.get("name").get(0));
				dto.setForbidden(map.get("forbidden"));
				dto.setLimited(map.get("limited"));
				dto.setSemiLimited(map.get("semiLimited"));
				return dto;
			} catch (Exception e) {
				return null;
			}
		}).collect(Collectors.toList());
	}
	
	private List<String> searchUrlList(String latestUrl) throws Exception {
		return getLimitUrls(latestUrl);
	}
}