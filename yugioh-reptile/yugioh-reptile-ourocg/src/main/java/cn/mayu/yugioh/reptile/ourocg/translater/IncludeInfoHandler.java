package cn.mayu.yugioh.reptile.ourocg.translater;

import java.util.List;
import java.util.stream.Collectors;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;
import cn.mayu.yugioh.common.core.html.DefaultHtmlHandler;
import cn.mayu.yugioh.common.core.html.HtmlParser;
import cn.mayu.yugioh.common.mongo.entity.IncludeInfo;

@Component
public class IncludeInfoHandler extends DefaultHtmlHandler<List<IncludeInfo>> {
	
	private static final String INCLUDE_INFO_TAG = "table";
	
	private static final int INCLUDE_INFO_TAG_INDEX = 0;
	
	@Override
	protected List<IncludeInfo> htmlTranslate(HtmlParser parser) {
		String html = parser.getRes();
		//Document doc = Jsoup.parse(html);
		//Elements els = doc.getElementsByTag(INCLUDE_INFO_TAG);
		//if (els.size() <= 0) {
			//return null;
		//}
		
		//return includeParse(els.get(INCLUDE_INFO_TAG_INDEX).toString());
		
//		parser = parser.parseByTagIndex(INCLUDE_INFO_TAG, INCLUDE_INFO_TAG_INDEX);
//		parser = parser.parseByTag("tr");
//		parser = parser.parseByTagIndex("td", 2);
		for (Object s : parser.parseByTag("td")) {
			System.out.println(s);
		}
//		for (int i = 3; i <= Jsoup.parse(html).getElementsByTag("td").size(); i+=3) {
//			System.out.println(Jsoup.parse(html).getElementsByTag("td").get(i - 3).html());
//			System.out.println(Jsoup.parse(html).getElementsByTag("td").get(i - 2).html());
//			System.out.println(Jsoup.parse(html).getElementsByTag("td").get(i - 1).html());
//			System.out.println();
//		}
		return null;
	}
	
	private List<IncludeInfo> includeParse(String xml) {
		Document document = Jsoup.parse(xml);
		return document.getElementsByTag("tr")
				       .stream()
				       .map(el -> el.getElementsByTag("td"))
				       .filter(els -> els.size() == 3)
				       .map(els -> combo(els))
				       .collect(Collectors.toList());
	}

	private IncludeInfo combo(Elements els) {
		IncludeInfo info = new IncludeInfo();
		String number = els.get(1).html();
		if (number.indexOf("-") != -1) {
			info.setShortName(number.substring(0, number.indexOf("-")));
		}

		info.setNumber(number);
		info.setPackName(els.get(0).getElementsByTag("a").html());
		info.setRace(els.get(2).html());
		info.setSellTime(els.get(0).getElementsByTag("small").html());
		return info;
	}
}
