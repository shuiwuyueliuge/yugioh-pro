package cn.mayu.yugioh.cardsource.core.ourocg.html;

import cn.mayu.yugioh.cardsource.basic.html.DefaultHtmlHandler;
import cn.mayu.yugioh.cardsource.basic.html.HtmlParser;
import cn.mayu.yugioh.cardsource.basic.interceptor.HttpStatusCodeInterceptor;
import cn.mayu.yugioh.cardsource.basic.interceptor.HttpStatusCodeInterceptorChain;

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
        super.addHttpStatusCodeInterceptor(chain);
        chain.addInterceptor(new ErrorInterceptor());
    }

    private static class ErrorInterceptor implements HttpStatusCodeInterceptor {

        @Override
        public void handelStatusCode(String url, HtmlParser parser) {
            if (parser.getStateCode() == 500) {
                throw new RuntimeException(parser.getResponse().toString());
            }
        }
    }
}
