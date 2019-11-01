package cn.mayu.yugioh.reptile.ourocg.translater;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

import cn.mayu.yugioh.common.core.html.DefaultHtmlHandler;
import cn.mayu.yugioh.common.core.html.HtmlParser;
import cn.mayu.yugioh.common.mongo.entity.LimitEntity;

@Component
public class LimitDataHandler extends DefaultHtmlHandler<List<LimitEntity>> {

	@Override
	protected List<LimitEntity> htmlTranslate(HtmlParser parser) throws Exception {
		String html = parser.getRes();
		List<String> urls = limitUrls(html);
		
		List<LimitEntity> list = new ArrayList<LimitEntity>();
		for (String string : urls) {
			Map<String, List<String>> map = getLimitHashId(string);
			LimitEntity dto = new LimitEntity();
			dto.setName(map.get("name").get(0));
			dto.setForbidden(map.get("forbidden"));
			dto.setLimited(map.get("limited"));
			dto.setSemiLimited(map.get("semiLimited"));
			list.add(dto);
		}
		
		return list;
	}
	
	private List<String> limitUrls(String html) throws Exception {
		Document doc = Jsoup.parse(html);
		return doc.getElementsByClass("limit-list sidebar-list")
				  .get(0)
				  .getElementsByTag("li")
				  .stream()
				  .map(el -> el.getElementsByTag("a").get(0).attr("href"))
				  .collect(Collectors.toList());
	}
	
	private Map<String, List<String>> getLimitHashId(String url) throws Exception {
		Document doc = Jsoup.connect(url).get();
		String name = doc.getElementsByClass("title").html();
		List<String> names = new ArrayList<String>(1);
		names.add(name);
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		map.put("name", names);
		List<String> forbiddens = doc.getElementsByTag("tbody").get(0).getElementsByTag("tr").stream().map(el -> {
			String str = el.getElementsByTag("a").get(0).attr("href");
			str = str.substring(str.lastIndexOf("/") + 1, str.length());
			return str;
		}).collect(Collectors.toList());
		map.put("forbidden", forbiddens);
		List<String> limiteds = doc.getElementsByTag("tbody").get(1).getElementsByTag("tr").stream().map(el -> {
			String str = el.getElementsByTag("a").get(0).attr("href");
			str = str.substring(str.lastIndexOf("/") + 1, str.length());
			return str;
		}).collect(Collectors.toList());
		map.put("limited", limiteds);
		List<String> semiLimiteds = doc.getElementsByTag("tbody").get(2).getElementsByTag("tr").stream().map(el -> {
			String str = el.getElementsByTag("a").get(0).attr("href");
			str = str.substring(str.lastIndexOf("/") + 1, str.length());
			return str;
		}).collect(Collectors.toList());
		map.put("semiLimited", semiLimiteds);
		return map;
	}
}
