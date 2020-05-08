package cn.mayu.yugioh.cardsource.html.interceptor;

import cn.mayu.yugioh.cardsource.html.HtmlParser;

public interface HttpStatusCodeInterceptor {

	void handelStatusCode(String url, HtmlParser htmlParser);
}
