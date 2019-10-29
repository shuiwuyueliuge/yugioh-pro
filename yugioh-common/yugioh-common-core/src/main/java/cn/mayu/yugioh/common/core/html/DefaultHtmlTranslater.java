package cn.mayu.yugioh.common.core.html;

import java.util.concurrent.TimeUnit;
import cn.mayu.yugioh.common.core.util.HtmlUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class DefaultHtmlTranslater<T> implements HtmlTranslater<T> {
	
	private static final String RETRY_AFTER = "Retry-After";
	
	public T visit(String url) throws Exception {
		VisitResponse response = HtmlUtil.connect(url);
		int statusCode = response.getStatusCode();
		String retryAfter = response.getHeaders().get(RETRY_AFTER);
		if (retry(statusCode, url, retryAfter)) {
			return visit(url);
		}
		
		return htmlTranslate(response.getHtml());
	}
	
	protected abstract T htmlTranslate(String html);

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
		
		TimeUnit.SECONDS.sleep(time <= 0 ? 1L : time);
	}
}
