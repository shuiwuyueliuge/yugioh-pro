package cn.mayu.yugioh.cardsource.basic.interceptor;

import cn.mayu.yugioh.cardsource.basic.html.HtmlParser;
import java.util.ArrayList;
import java.util.List;

public class HttpStatusCodeInterceptorChain implements HttpStatusCodeInterceptor {
	
	private List<HttpStatusCodeInterceptor> chain;
	
	{
		chain = new ArrayList<>();
	}

	@Override
	public void handelStatusCode(String url, HtmlParser htmlParser) {
		chain.stream().forEach(interceptor -> interceptor.handelStatusCode(url, htmlParser));
	}
	
	public HttpStatusCodeInterceptorChain addInterceptor(HttpStatusCodeInterceptor interceptor) {
		chain.add(interceptor);
		return this;
	}
}
