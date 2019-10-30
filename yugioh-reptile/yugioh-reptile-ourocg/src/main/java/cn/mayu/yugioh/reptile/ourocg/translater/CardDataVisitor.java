package cn.mayu.yugioh.reptile.ourocg.translater;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;
import cn.mayu.yugioh.common.core.html.DefaultHtmlVisitor;

@Component
public class CardDataVisitor extends DefaultHtmlVisitor<String> {
	
	private static final String CARD_DATA_TAG = "script";
	
	private static final int CARD_DATA_TAG_INDEX = 2;
	
	private static final String REPLACE_SOURCE = "package";
	
	private static final String REPLACE_TARGET = "packages";
	
	private static final String SUB_START = "{";
	
	private static final String SUB_END = "}";

	@Override
	protected String htmlTranslate(String html) {
		Document doc = Jsoup.parse(html);
		Elements els = doc.getElementsByTag(CARD_DATA_TAG);
		return cardDataFilter(els.get(CARD_DATA_TAG_INDEX).toString());
	}
	
	private String cardDataFilter(String metaData) {
		return metaData.substring(metaData.indexOf(SUB_START), metaData.lastIndexOf(SUB_END) + 1)
				       .replace(REPLACE_SOURCE, REPLACE_TARGET);
	}
}
