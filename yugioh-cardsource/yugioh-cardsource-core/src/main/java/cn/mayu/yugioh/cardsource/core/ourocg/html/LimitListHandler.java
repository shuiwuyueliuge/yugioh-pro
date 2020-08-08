package cn.mayu.yugioh.cardsource.core.ourocg.html;

import cn.mayu.yugioh.cardsource.basic.html.DefaultHtmlHandler;
import cn.mayu.yugioh.cardsource.basic.html.HtmlParser;
import cn.mayu.yugioh.common.dto.cardsource.LimitData;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class LimitListHandler extends DefaultHtmlHandler<List<LimitData>> {

	@Override
	protected List<LimitData> htmlTranslate(HtmlParser parser) {
		String[] res = parser.parseByClassIndex("limit-list sidebar-list", 0).parseByTag("li");
		List<String> urls = Stream.of(res).map(a -> parser.setHtml(a).parseByTagIndexAttr("a", 0, "href").toString()).collect(Collectors.toList());
		return urls.stream().map(data -> { // example: https://www.ourocg.cn/LimitO-20200701  https://www.ourocg.cn/LimitT-20200601
			LimitData limitData = new LimitData();
			limitData.setUrl(data);
			String[] array = data.split("-");
			limitData.setTime(array[1]);
			limitData.setOcgTcg(array[0].indexOf("O") == -1 ? "TCG" : "OCG");
			return limitData;
		}).collect(Collectors.toList());
	}
}