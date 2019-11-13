package cn.mayu.yugioh.reptile.ourocg.handler;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import cn.mayu.yugioh.common.core.html.DefaultHtmlHandler;
import cn.mayu.yugioh.common.core.html.HtmlParser;
import cn.mayu.yugioh.common.mongo.entity.IncludeInfo;
import cn.mayu.yugioh.reptile.ourocg.model.CardDetil;

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
			IncludeInfo info = new IncludeInfo();
			String number = res[i - 2];
			if (number.indexOf("-") != -1) {
				info.setShortName(number.substring(0, number.indexOf("-")));
			}
			
			info.setPackName(parser.setHtml(res[i - 3]).parseByTagIndex("a", 0).toString());
			info.setSellTime(parser.setHtml(res[i - 3]).parseByTagIndex("small", 0).toString());
			info.setNumber(number);
			info.setRace(res[i - 1]);
			infos.add(info);
		}
	}
	
	private String parseAdjust(HtmlParser parser) {
		try {
			return parser.parseByClassIndex("wiki", 0).parseByTagIndex("li", 1).toString();
		} catch (Exception e) {
			return "";
		}
	}
}