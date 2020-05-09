package cn.mayu.yugioh.cardsource.ourocg.html;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.stereotype.Component;
import cn.mayu.yugioh.cardsource.html.DefaultHtmlHandler;
import cn.mayu.yugioh.cardsource.html.HtmlParser;

@Component
public class LimitListHandler extends DefaultHtmlHandler<List<String>> {

	@Override
	protected List<String> htmlTranslate(HtmlParser parser) {
		String[] res = parser.parseByClassIndex("limit-list sidebar-list", 0).parseByTag("li");
		return Stream.of(res).map(a -> {
			return parser.setHtml(a).parseByTagIndexAttr("a", 0, "href").toString();
		}).collect(Collectors.toList());
	}
}