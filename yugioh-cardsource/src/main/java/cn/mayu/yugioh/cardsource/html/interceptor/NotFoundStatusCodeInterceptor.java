package cn.mayu.yugioh.cardsource.html.interceptor;

import cn.mayu.yugioh.cardsource.html.HtmlParser;

public class NotFoundStatusCodeInterceptor implements HttpStatusCodeInterceptor {
	
	@Override
	public void handelStatusCode(String url, HtmlParser parser) {
		if (parser.getStateCode() == 404) throw new RuntimeException(url + " is 404");
	}
}
