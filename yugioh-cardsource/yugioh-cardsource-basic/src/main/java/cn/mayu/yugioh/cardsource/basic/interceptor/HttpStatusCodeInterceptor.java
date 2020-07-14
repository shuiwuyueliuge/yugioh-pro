package cn.mayu.yugioh.cardsource.basic.interceptor;

import cn.mayu.yugioh.cardsource.basic.html.HtmlParser;

public interface HttpStatusCodeInterceptor {

	void handelStatusCode(String url, HtmlParser htmlParser);
}
