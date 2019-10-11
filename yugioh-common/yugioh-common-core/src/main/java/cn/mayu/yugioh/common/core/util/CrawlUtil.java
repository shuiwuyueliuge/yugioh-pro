package cn.mayu.yugioh.common.core.util;

import java.util.Map;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import lombok.Builder;
import lombok.Data;
import org.jsoup.Connection.Response;

public class CrawlUtil {

	private static final String AGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko)  Chrome/56.0.2924.87 Safari/537.36";

	private static Response getResponse(String url) throws Exception {
		return Jsoup.connect(url).ignoreContentType(false).timeout(50000).userAgent(AGENT).ignoreHttpErrors(true).execute();
	}

	private static Document getDocument(Response res) throws Exception {
		return res.parse();
	}
	
	public static CrawlResponse getElementsByTag(String url, String tagName, int num) throws Exception {
		Response res = getResponse(url);
		return CrawlResponse.builder().statusCode(res.statusCode())
				                      .headers(res.headers())
				                      .data(getDocument(res).getElementsByTag(tagName).get(num).toString())
				                      .build();
	}

	@Data
	@Builder
	public static class CrawlResponse {

		private int statusCode;

		private Map<String, String> headers;

		private String data;
	}
}
