package cn.mayu.yugioh.reptile.ourocg.manager;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import cn.mayu.yugioh.reptile.ourocg.model.CardLimitEntity;
import static cn.mayu.yugioh.common.core.util.CrawlUtil.*;

@Component
public class CardDataFindManagerImpl implements CardDataFindManager {

	private Logger log = LoggerFactory.getLogger(getClass());

	private static final String RETRY_AFTER = "Retry-After";
	
	private static final String META_DATA_ZONE = "script";
	
	private static final String PACKAGE_DATA_ZONE = "table";
	
	private static final String REPLACE_SOURCE = "package";
	
	private static final String REPLACE_TARGET = "packages";
	
	private static final String SUB_START = "{";
	
	private static final String SUB_END = "}";

	@Override
	public String findMetaData(String url) throws Exception {
		CrawlResponse response = getCrawlResponseByTag(url, META_DATA_ZONE, 2);
		if (retry(response.getStatusCode(), url, response.getHeaders().get(RETRY_AFTER))) {
			return findMetaData(url);
		}

		return metaDataFilter(response.getData());
	}
	
	@Override
	public List<String> findPackageData(String url) throws Exception {
		CrawlResponse response = getCrawlResponseByTag(url, PACKAGE_DATA_ZONE, 0);
		if (retry(response.getStatusCode(), url, response.getHeaders().get(RETRY_AFTER))) {
			return findPackageData(url);
		}
		
		return includeParse(response.getData());
	}
	
	private String metaDataFilter(String metaData) {
		return metaData.substring(metaData.indexOf(SUB_START), metaData.lastIndexOf(SUB_END) + 1).replace(REPLACE_SOURCE, REPLACE_TARGET);
	}

	private boolean retry(int statusCode, String url, String header) throws Exception {
		if (statusCode == 429 && header != null) {
			sleep(statusCode, url, header);
			return true;
		}

		if (statusCode != 200) {
			log.error("connect [{}] status code is [{}]", url, statusCode);
		}
		
		return false;
	}
	
	private void sleep(int statusCode, String url, String header) throws Exception {
		long time = Long.parseLong(header);
		if (log.isDebugEnabled()) {
			log.debug("connect [{}] status code is [{}] and retry-after is [{}]", url, statusCode, header);
		}
		
		TimeUnit.SECONDS.sleep(time <= 0 ? 1L : time);
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