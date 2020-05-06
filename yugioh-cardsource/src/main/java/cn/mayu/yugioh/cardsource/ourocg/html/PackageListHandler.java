package cn.mayu.yugioh.cardsource.ourocg.html;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.stereotype.Component;
import cn.mayu.yugioh.common.core.html.DefaultHtmlHandler;
import cn.mayu.yugioh.common.core.html.HtmlParser;

@Component
public class PackageListHandler extends DefaultHtmlHandler<List<String>> {

	@Override
	protected List<String> htmlTranslate(HtmlParser parser) {
		String[] res = parser.parseByClassIndex("package-view package-list", 0).parseByTagAttr("a", "href");
		return Stream.of(res).collect(Collectors.toList());
	}
}
