package cn.mayu.yugioh.reptile.ourocg;

import java.util.concurrent.TimeUnit;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.jsoup.Connection.Response;

@Component
public class CardMetaDataFindManager {

	private Logger log = LoggerFactory.getLogger(getClass());
	
	private static final String AGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko)  Chrome/56.0.2924.87 Safari/537.36";
	
	private static final String RETRY_AFTER = "retry-after";
	
	public String exec(String url, int num) {
		try {
			Connection connection = Jsoup.connect(String.format(url, num)).ignoreContentType(false).userAgent(AGENT).ignoreHttpErrors(true);
			Response response = connection.execute();
			if (isSleep(response)) {
				return exec(url, num);
			}
			
			return response.parse().getElementsByTag("script").get(2).toString().replace(" ", "").replace("\n", "");
		} catch (Exception e) {
			log.error("find card data error [{}]", e);
			return null;
		}
	}
	
	private boolean isSleep(Response response) throws Exception {
		if (response.statusCode() == 200) {
			return false;
		}
		
		if (response.statusCode() == 429 || response.hasHeader(RETRY_AFTER)) {
			log.info("connect [{}] status code is [{}] and retry-after is [{}]", response.url(), response.statusCode(), response.header(RETRY_AFTER));
			long time = Long.parseLong(response.header(RETRY_AFTER));
			if (time > 0) {
				TimeUnit.SECONDS.sleep(time);
			}
			
			return true;
		}
		
		log.error("connect [{}] status code is [{}]", response.url(), response.statusCode());
		return false;
	}
}
