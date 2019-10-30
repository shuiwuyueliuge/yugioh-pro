package cn.mayu.yugioh.common.core.util;

import org.jsoup.Jsoup;
import cn.mayu.yugioh.common.core.html.VisitResponse;
import org.jsoup.Connection.Response;

public class HtmlUtil {
	
	private static final String AGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko)  Chrome/56.0.2924.87 Safari/537.36";

	private static final int TIMEOUT_MILLIS = 50000;
	
	public static VisitResponse connect(String url) throws Exception {
		Response res = getResponse(url);
		return new VisitResponse(res.statusCode(), res.headers(), res.body());
	}
	
	private static Response getResponse(String url) throws Exception {
		return Jsoup.connect(url)
				    .ignoreContentType(false)
				    .timeout(TIMEOUT_MILLIS)
				    .userAgent(AGENT)
				    .ignoreHttpErrors(true)
				    .execute();
	}
	
	public static String getElementsByTag(String html, String tag, int index) {
		return Jsoup.parse(html).getElementsByTag(tag).get(index).toString();
	}

	public static String getElementsByClass(String html, String className, int index) {
		return Jsoup.parse(html).getElementsByClass(className).get(index).toString();
	}
}
