package cn.mayu.yugioh.common.core.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import lombok.Builder;
import lombok.Data;
import org.jsoup.Connection.Response;

public class CrawlUtil {

	private static final String AGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko)  Chrome/56.0.2924.87 Safari/537.36";

	private static Response getResponse(String url) throws Exception {
		return Jsoup.connect(url).ignoreContentType(false).timeout(50000).userAgent(AGENT).ignoreHttpErrors(true).execute();
	}

	private static Document getDocument(Response res) throws Exception {
		return Jsoup.parse(res.body());
	}
	
	public static CrawlResponse getCrawlResponseByTag(String url, String tagName, int num) throws Exception {
		Response res = getResponse(url);
		String data = null;
		Elements els = getDocument(res).getElementsByTag(tagName);
		if (els.size() > 0) data = els.get(num).toString();
		return CrawlResponse.builder().statusCode(res.statusCode())
				                      .headers(res.headers())
				                      .data(data)
				                      .build();
	}
	
	public static List<String> includeParse(String xml) {
		if (xml == null) {
			return new ArrayList<String>();
		}
		
		Document document = Jsoup.parse(xml);
		return document.getElementsByTag("tr")
				       .stream()
		               .map(el -> el.getElementsByTag("td"))
		               .filter(els -> els.size() == 3)
		               .map(CrawlUtil::combo)
		               .collect(Collectors.toList());
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
	@Builder
	public static class CrawlResponse {

		private int statusCode;

		private Map<String, String> headers;

		private String data;
	}
}
