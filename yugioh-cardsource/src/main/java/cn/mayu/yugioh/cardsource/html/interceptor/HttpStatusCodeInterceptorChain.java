package cn.mayu.yugioh.cardsource.html.interceptor;

import java.util.ArrayList;
import java.util.List;
import cn.mayu.yugioh.cardsource.html.HtmlParser;

public class HttpStatusCodeInterceptorChain implements HttpStatusCodeInterceptor {
	
	private List<HttpStatusCodeInterceptor> chain;
	
	{
		chain = new ArrayList<HttpStatusCodeInterceptor>();
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
