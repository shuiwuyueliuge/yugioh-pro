package cn.mayu.yugioh.cardsource.ourocg.html;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import cn.mayu.yugioh.cardsource.ourocg.model.CardDetil;
import cn.mayu.yugioh.cardsource.ourocg.model.IncludeInfo;
import cn.mayu.yugioh.common.core.html.DefaultHtmlHandler;
import cn.mayu.yugioh.common.core.html.HtmlParser;

@Component
public class IncludeInfoHandler extends DefaultHtmlHandler<CardDetil> {
	
	@Override
	protected CardDetil htmlTranslate(HtmlParser parser) {
		String html = parser.toString();
		String[] res = parser.parseByTag("td");
		List<IncludeInfo> infos = new ArrayList<IncludeInfo>();
		collectToList(infos, res, parser);
		String adjust = parseAdjust(parser.setHtml(html));
		return new CardDetil(infos, adjust);
	}
	
	private void collectToList(List<IncludeInfo> infos, String[] res, HtmlParser parser) {
		for (int i = 3; i <= res.length; i += 3) {
			IncludeInfo builder = new IncludeInfo();
			String number = res[i - 2];
			if (number.indexOf("-") != -1) {
				builder.setShortName(number.substring(0, number.indexOf("-")));
			}
			
			builder.setPackName(parser.setHtml(res[i - 3]).parseByTagIndex("a", 0).toString());
			builder.setHref(parser.setHtml(res[i - 3]).parseByTagAttr("a", "href")[0]);
			builder.setSellTime(parser.setHtml(res[i - 3]).parseByTagIndex("small", 0).toString());
			builder.setNumber(number);
			builder.setRare(res[i - 1]);
			infos.add(builder);
		}
	}
	
	private String parseAdjust(HtmlParser parser) {
		try {
			String adjust = parser.parseByClassIndex("wiki", 0).parseByTagIndex("li", 1).toString();
		    return adjust.equals("　　　　") ? "" : adjust;
		} catch (Exception e) {
			return "";
		}
	}
}
