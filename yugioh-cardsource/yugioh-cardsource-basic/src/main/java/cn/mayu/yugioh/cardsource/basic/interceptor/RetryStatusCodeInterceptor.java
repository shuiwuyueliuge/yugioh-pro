package cn.mayu.yugioh.cardsource.basic.interceptor;

import cn.mayu.yugioh.cardsource.basic.html.HtmlParser;
import cn.mayu.yugioh.cardsource.basic.html.VisitResponse;
import lombok.extern.slf4j.Slf4j;
import static java.util.concurrent.TimeUnit.SECONDS;

@Slf4j
public class RetryStatusCodeInterceptor implements HttpStatusCodeInterceptor {
	
	private static final String RETRY_AFTER = "Retry-After";

	@Override
	public void handelStatusCode(String url, HtmlParser parser) {
		VisitResponse response = parser.getResponse();
		int statusCode = response.getStatusCode();
		String retryAfter = response.getHeaders().get(RETRY_AFTER);
		if (retry(statusCode, url, retryAfter)) {
			parser.connUrl();
		}
	}
	
	private boolean retry(int statusCode, String url, String retryAfter) {
		if (statusCode == 429 && retryAfter != null) {
			sleep(statusCode, url, retryAfter);
			return true;
		}

		return false;
	}

	private void sleep(int statusCode, String url, String retryAfter) {
		long time = Long.parseLong(retryAfter);
		if (log.isDebugEnabled()) {
			log.debug("connect [{}] status code is [{}] and retry-after is [{}]", url, statusCode, retryAfter);
		}

		try {
			SECONDS.sleep(time <= 0 ? 1L : time);
		} catch (InterruptedException e) {
		}
	}
}
