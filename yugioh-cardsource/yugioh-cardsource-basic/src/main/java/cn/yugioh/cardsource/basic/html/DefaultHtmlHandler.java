package cn.yugioh.cardsource.basic.html;

import cn.yugioh.cardsource.basic.interceptor.HttpStatusCodeInterceptorChain;
import cn.yugioh.cardsource.basic.interceptor.NotFoundStatusCodeInterceptor;
import cn.yugioh.cardsource.basic.interceptor.RetryStatusCodeInterceptor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class DefaultHtmlHandler<T> implements HtmlHandler<T> {
	
	public T handle(String url) {
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
	
	protected abstract T htmlTranslate(HtmlParser parser);
	
	protected void addHttpStatusCodeInterceptor(HttpStatusCodeInterceptorChain chain) {
		chain.addInterceptor(new RetryStatusCodeInterceptor())
	     .addInterceptor(new NotFoundStatusCodeInterceptor());
	}
}
