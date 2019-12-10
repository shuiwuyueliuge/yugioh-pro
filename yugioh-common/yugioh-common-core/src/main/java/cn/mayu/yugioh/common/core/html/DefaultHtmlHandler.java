package cn.mayu.yugioh.common.core.html;

import static java.util.concurrent.TimeUnit.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class DefaultHtmlHandler<T> implements HtmlHandler<T> {
	
	private static final String RETRY_AFTER = "Retry-After";
	
	public T handle(String url) throws Exception {
		HtmlParser parser = initParser(url);
		VisitResponse response = parser.getResponse();
		if (log.isDebugEnabled()) {
			log.debug("connect [{}] status code is [{}] and response-header is [{}]", url, response.getStatusCode(), response.getHeaders());
		}
		
		int statusCode = response.getStatusCode();
		String retryAfter = response.getHeaders().get(RETRY_AFTER);
		if (retry(statusCode, url, retryAfter)) {
			return handle(url);
		}
		
		return htmlTranslate(parser);
	}
	
	protected abstract T htmlTranslate(HtmlParser parser) throws Exception;
	
	private HtmlParser initParser(String url) {
		return new HtmlParser(url);
	}

	private boolean retry(int statusCode, String url, String retryAfter) throws Exception {
		if (statusCode == 429 && retryAfter != null) {
			sleep(statusCode, url, retryAfter);
			return true;
		}

		if (statusCode != 200) {
			log.error("connect [{}] status code is [{}]", url, statusCode);
		}
		
		return false;
	}
	
	private void sleep(int statusCode, String url, String retryAfter) throws Exception {
		long time = Long.parseLong(retryAfter);
		if (log.isDebugEnabled()) {
			log.debug("connect [{}] status code is [{}] and retry-after is [{}]", url, statusCode, retryAfter);
		}
		
		SECONDS.sleep(time <= 0 ? 1L : time);
	}
}
