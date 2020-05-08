package cn.mayu.yugioh.cardsource.ourocg.html;

import cn.mayu.yugioh.cardsource.html.DefaultHtmlHandler;
import cn.mayu.yugioh.cardsource.html.HtmlParser;
import cn.mayu.yugioh.cardsource.html.interceptor.HttpStatusCodeInterceptorChain;
import cn.mayu.yugioh.cardsource.html.interceptor.NotFoundStatusCodeInterceptor;
import cn.mayu.yugioh.cardsource.html.interceptor.RetryStatusCodeInterceptor;

public class CardDataHtmlHandler extends DefaultHtmlHandler<String> {
	
	private static final String CARD_DATA_TAG = "script";
	
	private static final int CARD_DATA_TAG_INDEX = 2;
	
	private static final String REPLACE_SOURCE = "package";
	
	private static final String REPLACE_TARGET = "packages";
	
	private static final String SUB_START = "{";
	
	private static final String SUB_END = "}";

	@Override
	protected String htmlTranslate(HtmlParser parser) {
		String data = parser.parseByTagIndex(CARD_DATA_TAG, CARD_DATA_TAG_INDEX).toString();
		return cardDataFilter(data);
	}
	
	private String cardDataFilter(String metaData) {
		return metaData.substring(metaData.indexOf(SUB_START), metaData.lastIndexOf(SUB_END) + 1)
				       .replace(REPLACE_SOURCE, REPLACE_TARGET);
	}

	@Override
	protected void addHttpStatusCodeInterceptor(HttpStatusCodeInterceptorChain chain) {
		chain.addInterceptor(new RetryStatusCodeInterceptor())
		     .addInterceptor(new NotFoundStatusCodeInterceptor());
	}
}
