package cn.yugioh.cardsource.basic.interceptor;

import cn.yugioh.cardsource.basic.html.HtmlParser;

public interface HttpStatusCodeInterceptor {

	void handelStatusCode(String url, HtmlParser htmlParser);
}
