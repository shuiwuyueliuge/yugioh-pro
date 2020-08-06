package cn.mayu.yugioh.cardsource.core.ourocg.html;

import cn.mayu.yugioh.cardsource.basic.html.DefaultHtmlHandler;
import cn.mayu.yugioh.cardsource.basic.html.HtmlParser;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class LimitListHandler extends DefaultHtmlHandler<List<String>> {

	@Override
	protected List<String> htmlTranslate(HtmlParser parser) {
		String[] res = parser.parseByClassIndex("limit-list sidebar-list", 0).parseByTag("li");
		return Stream.of(res).map(a -> parser.setHtml(a).parseByTagIndexAttr("a", 0, "href").toString()).collect(Collectors.toList());
	}
}