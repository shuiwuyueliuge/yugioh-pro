package cn.mayu.yugioh.reptile.ourocg.manager;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
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
		if (isSleep(response.getStatusCode(), url, response.getHeaders().get(RETRY_AFTER))) {
			return findMetaData(url);
		}

		return metaDataFilter(response.getData());
	}
	
	private String metaDataFilter(String metaData) {
		return metaData.substring(metaData.indexOf(SUB_START), metaData.lastIndexOf(SUB_END) + 1).replace(REPLACE_SOURCE, REPLACE_TARGET);
	}
	
	@Override
	public List<String> findPackageData(String url) throws Exception {
		CrawlResponse response = getCrawlResponseByTag(url, PACKAGE_DATA_ZONE, 0);
		if (isSleep(response.getStatusCode(), url, response.getHeaders().get(RETRY_AFTER))) {
			return findPackageData(url);
		}
		
		return includeParse(response.getData());
	}

	private boolean isSleep(int statusCode, String url, String header) throws Exception {
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
}

//每月执行一次
//ourocg拉取全部卡片的数据保存到本地文件，队列多次写入√
//从本地文件每张卡md5签名 - mongo查找指定id的数据 - 没有就查询卡片详细信息写入
//                                          |
//                                         判断md5是否相同 - 相同不操作
//                                          |
//                                         不相同查询卡片详细信息写入mongo