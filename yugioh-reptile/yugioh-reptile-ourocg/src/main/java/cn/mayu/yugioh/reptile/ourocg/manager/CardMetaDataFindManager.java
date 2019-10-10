package cn.mayu.yugioh.reptile.ourocg.manager;

import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import static cn.mayu.yugioh.common.core.util.CrawlUtil.*;

@Component
public class CardMetaDataFindManager {

	private Logger log = LoggerFactory.getLogger(getClass());

	private static final String RETRY_AFTER = "Retry-After";
	
	private static final String DATA_ZONE = "script";

	public String doExec(String url) throws Exception {
		CrawlResponse response = getElementsByTag(url, DATA_ZONE, 2);
		if (isSleep(response.getStatusCode(), url, response.getHeaders().get(RETRY_AFTER))) {
			return doExec(url);
		}

		return response.getData().replace(" ", "").replace("\n", "");
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
		if (time <= 0) {
			return;
		}
		
		if (log.isDebugEnabled()) {
			log.debug("connect [{}] status code is [{}] and retry-after is [{}]", url, statusCode, header);
		}
		
		TimeUnit.SECONDS.sleep(time);
	}
}
