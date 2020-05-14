package cn.mayu.yugioh.cardsource.ourocg.html;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.stereotype.Component;
import cn.mayu.yugioh.cardsource.html.DefaultHtmlHandler;
import cn.mayu.yugioh.cardsource.html.HtmlParser;
import cn.mayu.yugioh.cardsource.ourocg.model.LimitInfo;

@Component
public class LimitDataHandler extends DefaultHtmlHandler<LimitInfo> {

	@Override
	protected LimitInfo htmlTranslate(HtmlParser parser) {
		LimitInfo entity = new LimitInfo();
		String html = parser.toString();
		String[] tbodys = parser.parseByTag("tbody");
		List<List<String>> hashIds = parseLimitList(tbodys, parser);
		String tatile = parser.setHtml(html).parseByClassIndex("title", 0).toString();
		entity.setName(tatile);
		entity.setForbidden(hashIds.get(0));
		entity.setLimited(hashIds.get(1));
		entity.setSemiLimited(hashIds.get(2));
		return entity;
	}

	private List<List<String>> parseLimitList(String[] tbodys, HtmlParser parser) {
		return Stream.of(tbodys).map(data -> parseCardId(data, parser)).collect(Collectors.toList());
	}

	private List<String> parseCardId(String data, HtmlParser parser) {
		String[] s = parser.setHtml(data).parseByTag("a");
		return Stream.of(s).collect(Collectors.toList());
	}
}