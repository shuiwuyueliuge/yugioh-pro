package cn.mayu.yugioh.cardsource.html;

import cn.mayu.yugioh.cardsource.html.interceptor.HttpStatusCodeInterceptorChain;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class DefaultHtmlHandler<T> implements HtmlHandler<T> {
	
	public T handle(String url) throws Exception {
		HtmlParser parser = new HtmlParser(url);
		VisitResponse response = parser.getResponse();
		if (log.isDebugEnabled()) {
			log.debug("connect [{}] status code is [{}] and response-header is [{}]", url, response.getStatusCode(), response.getHeaders());
		}
		
		HttpStatusCodeInterceptorChain chain = new HttpStatusCodeInterceptorChain();
		addHttpStatusCodeInterceptor(chain);
		chain.handelStatusCode(url, parser);
		return htmlTranslate(parser);
	}
	
	protected abstract T htmlTranslate(HtmlParser parser) throws Exception;
	
	protected abstract void addHttpStatusCodeInterceptor(HttpStatusCodeInterceptorChain chain);
}
