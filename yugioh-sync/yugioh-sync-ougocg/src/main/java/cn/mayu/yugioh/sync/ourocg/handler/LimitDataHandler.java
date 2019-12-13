package cn.mayu.yugioh.sync.ourocg.handler;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.stereotype.Component;
import cn.mayu.yugioh.common.core.html.DefaultHtmlHandler;
import cn.mayu.yugioh.common.core.html.HtmlParser;
import cn.mayu.yugioh.common.dto.sync.home.LimitProto;

@Component
public class LimitDataHandler extends DefaultHtmlHandler<List<LimitProto.LimitEntity>> {

	@Override
	protected List<LimitProto.LimitEntity> htmlTranslate(HtmlParser parser) throws Exception {
		String[] res = parser.parseByClassIndex("limit-list sidebar-list", 0).parseByTag("li");
		return Stream.of(res).map(tagOfA -> limitEntity(tagOfA, parser)).collect(Collectors.toList());
	}

	private LimitProto.LimitEntity limitEntity(String tagOfA, HtmlParser parser) {
		LimitProto.LimitEntity.Builder entity = LimitProto.LimitEntity.getDefaultInstance().toBuilder();
		String url = parseLimitUrl(parser, tagOfA);
		String html = parser.connUrl(url).toString();
		String tatile = parser.setHtml(html).parseByClassIndex("title", 0).toString();
		String[] tbodys = parser.setHtml(html).parseByTag("tbody");
		List<List<String>> hashIds = parseLimitList(tbodys, parser);
		entity.setName(tatile);
		try {
			entity.addAllForbidden(hashIds.get(0));
		} catch (Exception e) {
			System.out.println(hashIds);
		}
		entity.addAllLimited(hashIds.get(1));
		entity.addAllSemiLimited(hashIds.get(2));
		return entity.build();
	}

	private String parseLimitUrl(HtmlParser parser, String html) {
		return parser.setHtml(html).parseByTagIndexAttr("a", 0, "href").toString();
	}

	private List<List<String>> parseLimitList(String[] tbodys, HtmlParser parser) {
		return Stream.of(tbodys).map(data -> parseCardId(data, parser)).collect(Collectors.toList());
	}

	private List<String> parseCardId(String data, HtmlParser parser) {
		String[] s = parser.setHtml(data).parseByTagAttr("a", "href");
		return Stream.of(s).map(str -> str.substring(str.lastIndexOf("/") + 1, str.length())).collect(Collectors.toList());
	}
}