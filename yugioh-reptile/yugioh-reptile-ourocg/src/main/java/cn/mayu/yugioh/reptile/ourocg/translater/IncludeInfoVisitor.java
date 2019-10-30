package cn.mayu.yugioh.reptile.ourocg.translater;

import java.util.List;
import java.util.stream.Collectors;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;
import cn.mayu.yugioh.common.core.html.DefaultHtmlVisitor;
import cn.mayu.yugioh.common.mongo.entity.IncludeInfo;

@Component
public class IncludeInfoVisitor extends DefaultHtmlVisitor<List<IncludeInfo>> {
	
	private static final String INCLUDE_INFO_TAG = "table";
	
	private static final int INCLUDE_INFO_TAG_INDEX = 0;
	
	@Override
	protected List<IncludeInfo> htmlTranslate(String html) {
		Document doc = Jsoup.parse(html);
		Elements els = doc.getElementsByTag(INCLUDE_INFO_TAG);
		if (els.size() <= 0) {
			return null;
		}
		
		return includeParse(els.get(INCLUDE_INFO_TAG_INDEX).toString());
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
