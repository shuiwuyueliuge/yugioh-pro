package cn.yugioh.cardsource.core.ourocg.html;

import cn.yugioh.cardsource.basic.html.DefaultHtmlHandler;
import cn.yugioh.cardsource.basic.html.HtmlParser;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class PackageListHandler extends DefaultHtmlHandler<List<String>> {

	@Override
	protected List<String> htmlTranslate(HtmlParser parser) {
		String[] res = parser.parseByClassIndex("package-view package-list", 0).parseByTagAttr("a", "href");
		return Stream.of(res).collect(Collectors.toList());
	}
}
