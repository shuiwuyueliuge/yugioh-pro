package cn.mayu.yugioh.common.core.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.jsoup.Connection.Response;

public class CrawlUtil {

	private static final String AGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko)  Chrome/56.0.2924.87 Safari/537.36";

	public static CrawlResponse getCrawlResponseByTag(String url, String tagName, int num) throws Exception {
		Response res = getResponse(url);
		String data = null;
		Elements els = getDocument(res).getElementsByTag(tagName);
		if (els.size() > 0)
			data = els.get(num).toString();
		return new CrawlResponse(res.statusCode(), res.headers(), data);
	}

	public static List<String> getLimitUrls(String url) throws Exception {
		Response res = getResponse(url);
		return getDocument(res).getElementsByClass("limit-list sidebar-list").get(0).getElementsByTag("li").stream()
				.map(el -> el.getElementsByTag("a").get(0).attr("href")).collect(Collectors.toList());
	}

	public static Map<String, List<String>> getLimitHashId(String url) throws Exception {
		Document doc = getDocument(getResponse(url));
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

	private static Response getResponse(String url) throws Exception {
		return Jsoup.connect(url).ignoreContentType(false).timeout(50000).userAgent(AGENT).ignoreHttpErrors(true)
				.execute();
	}

	private static Document getDocument(Response res) throws Exception {
		return Jsoup.parse(res.body());
	}

	public static List<String> includeParse(String xml) {
		if (xml == null) {
			return new ArrayList<String>();
		}

		Document document = Jsoup.parse(xml);
		return document.getElementsByTag("tr").stream().map(el -> el.getElementsByTag("td"))
				.filter(els -> els.size() == 3).map(CrawlUtil::combo).collect(Collectors.toList());
	}

	private static String combo(Elements els) {
		String packName = els.get(0).getElementsByTag("a").html();
		String number = els.get(1).html();
		String shortName = "";
		if (number.indexOf("-") != -1) {
			shortName = number.substring(0, number.indexOf("-"));
		}

		String small = els.get(0).getElementsByTag("small").html();
		String race = els.get(2).html();
		String combo = "%s:%s:%s:%s:%s";
		return String.format(combo, packName, shortName, small, number, race);
	}

	@Data
	@AllArgsConstructor
	public static class CrawlResponse {

		private int statusCode;

		private Map<String, String> headers;

		private String data;
	}
}
