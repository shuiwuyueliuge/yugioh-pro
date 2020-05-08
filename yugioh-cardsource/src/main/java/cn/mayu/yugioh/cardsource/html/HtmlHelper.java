package cn.mayu.yugioh.cardsource.html;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Connection.Response;
import static cn.mayu.yugioh.common.core.util.AssertUtil.*;
import java.util.Collections;

@Slf4j
public class HtmlHelper {
	
	private static final String AGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko)  Chrome/56.0.2924.87 Safari/537.36";

	private static final int TIMEOUT_MILLIS = 50000;
	
	protected static VisitResponse connect(String url) {
		try {
			Response res = getResponse(url);
			return new VisitResponse(res.statusCode(), res.headers(), res.body());
		} catch (Exception e) {
			log.error("connect [{}] error [{}]", url, e);
			return new VisitResponse(0, Collections.emptyMap(), "");
		}
	}
	
	protected static String getElementsByTagIndex(String html, String tag, int index) {
		Elements els = parse(html).getElementsByTag(tag);
		return isIndexOutOfBounds(els, index) ? "" : els.get(index).html();
	}

	protected static String getElementsByClassIndex(String html, String className, int index) {
		Elements els = parse(html).getElementsByClass(className);
		return isIndexOutOfBounds(els, index) ? "" : els.get(index).html();
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

	protected static String[] getElementsByTag(String html, String tagName) {
		Elements els = parse(html).getElementsByTag(tagName);
		String res[] = new String[els.size()];
		for (int i = 0; i < els.size(); i++) {
			res[i] = els.get(i).html();
		}
		
		return res;
	}
	
	protected static String[] getElementsByTagAttr(String html, String tagName, String attr) {
		Elements els = parse(html).getElementsByTag(tagName);
		String res[] = new String[els.size()];
		for (int i = 0; i < els.size(); i++) {
			res[i] = els.get(i).attr(attr);
		}
		
		return res;
	}

	protected static String getElementsByTagIndexAttr(String html, String tagName, int index, String attribute) {
		Elements els = parse(html).getElementsByTag(tagName);
		return isIndexOutOfBounds(els, index) ? "" : els.get(index).attr(attribute);
	}
}
