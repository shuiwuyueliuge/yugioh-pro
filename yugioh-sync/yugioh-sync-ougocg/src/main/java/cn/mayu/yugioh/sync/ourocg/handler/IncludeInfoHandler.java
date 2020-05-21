package cn.mayu.yugioh.sync.ourocg.handler;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import cn.mayu.yugioh.common.core.html.DefaultHtmlHandler;
import cn.mayu.yugioh.common.core.html.HtmlParser;
import cn.mayu.yugioh.common.dto.sync.home.CardProto;
import cn.mayu.yugioh.sync.ourocg.model.CardDetil;

@Component
public class IncludeInfoHandler extends DefaultHtmlHandler<CardDetil> {
	
	@Override
	protected CardDetil htmlTranslate(HtmlParser parser) {
		String html = parser.toString();
		String[] res = parser.parseByTag("td");
		List<CardProto.IncludeInfo> infos = new ArrayList<CardProto.IncludeInfo>();
		collectToList(infos, res, parser);
		String adjust = parseAdjust(parser.setHtml(html));
		return new CardDetil(infos, adjust);
	}
	
	private void collectToList(List<CardProto.IncludeInfo> infos, String[] res, HtmlParser parser) {
		for (int i = 3; i <= res.length; i += 3) {
			CardProto.IncludeInfo.Builder builder = CardProto.IncludeInfo.getDefaultInstance().toBuilder();
			String number = res[i - 2];
			if (number.indexOf("-") != -1) {
				builder.setShortName(number.substring(0, number.indexOf("-")));
			}
			
			builder.setPackName(parser.setHtml(res[i - 3]).parseByTagIndex("a", 0).toString());
			builder.setSellTime(parser.setHtml(res[i - 3]).parseByTagIndex("small", 0).toString());
			builder.setNumber(number);
			builder.setRace(res[i - 1]);
			infos.add(builder.build());
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
