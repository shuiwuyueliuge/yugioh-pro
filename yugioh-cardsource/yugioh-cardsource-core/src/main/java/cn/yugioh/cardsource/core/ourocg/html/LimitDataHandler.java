package cn.yugioh.cardsource.core.ourocg.html;

import cn.yugioh.cardsource.basic.html.DefaultHtmlHandler;
import cn.yugioh.cardsource.basic.html.HtmlParser;
import cn.yugioh.cardsource.core.ourocg.model.LimitInfo;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
		getTimeAndType(entity, parser.getUrl());
		return entity;
	}

	private List<List<String>> parseLimitList(String[] tbodys, HtmlParser parser) {
		return Stream.of(tbodys).map(data -> parseCardId(data, parser)).collect(Collectors.toList());
	}

	private List<String> parseCardId(String data, HtmlParser parser) {
		String[] s = parser.setHtml(data).parseByTag("a");
		return Stream.of(s).collect(Collectors.toList());
	}

	private void getTimeAndType(LimitInfo entity, String url) {
		String[] str = url.split("/");
		String[] s = str[str.length - 1].split("-");
		String time = s[1];
		entity.setPublishTime(time);
		entity.setRegion(s[0].indexOf("T") != -1 ? "TCG" : "OCG");
	}
}