package cn.mayu.yugioh.common.core.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import cn.mayu.yugioh.common.core.html.VisitResponse;
import org.jsoup.Connection.Response;
import static cn.mayu.yugioh.common.core.util.AssertUtil.*;

public class HtmlUtil {
	
	private static final String AGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko)  Chrome/56.0.2924.87 Safari/537.36";

	private static final int TIMEOUT_MILLIS = 50000;
	
	public static VisitResponse connect(String url) throws Exception {
		Response res = getResponse(url);
		return new VisitResponse(res.statusCode(), res.headers(), res.body());
	}
	
	public static String getElementsByTagIndex(String html, String tag, int index) {
		Elements els = parse(html).getElementsByTag(tag);
		return isIndexOutOfBounds(els, index) ? "" : els.get(index).toString();
	}

	public static String getElementsByClassIndex(String html, String className, int index) {
		Elements els = parse(html).getElementsByClass(className);
		return isIndexOutOfBounds(els, index) ? "" : els.get(index).toString();
	}
	
	private static Response getResponse(String url) throws Exception {
		return Jsoup.connect(url)
				    .ignoreContentType(false)
				    .timeout(TIMEOUT_MILLIS)
				    .userAgent(AGENT)
				    .ignoreHttpErrors(true)
				    .execute();
	}
	
	private static Document parse(String html) {
		return Jsoup.parse(html);
	}

	public static String[] getElementsByTag(String html, String tagName) {
		Elements els = parse(html).getElementsByTag(tagName);
		String strs[] = new String[els.size()]; 
		return els.toArray(strs);
	}
}
