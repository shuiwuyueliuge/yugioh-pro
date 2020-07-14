package cn.mayu.yugioh.cardsource.core.ourocg.html;

import cn.mayu.yugioh.common.dto.cardsource.PackageData;
import cn.mayu.yugioh.cardsource.basic.html.DefaultHtmlHandler;
import cn.mayu.yugioh.cardsource.basic.html.HtmlParser;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class PackageListHandler extends DefaultHtmlHandler<List<PackageData>> {

	@Override
	protected List<PackageData> htmlTranslate(HtmlParser parser) {
		parser.parseByClassIndex("package-view package-list", 0);
		List<Map<String, String>> list = parser.parseTextAndAttrs("a", "html");
		return list.stream().map(data -> {
			PackageData packageData = new PackageData();
			packageData.setName(data.get("html"));
			packageData.setUri(data.get("href"));
			return packageData;
		}).collect(Collectors.toList());
	}
}
