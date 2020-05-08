package cn.mayu.yugioh.cardsource.html;

import static cn.mayu.yugioh.cardsource.html.HtmlHelper.*;

public class HtmlParser {

	private String html;

	private VisitResponse response;

	public HtmlParser(String url) {
		connUrl(url);
	}

	public HtmlParser connUrl(String url) {
		this.response = connect(url);
		this.html = getResponse().getHtml();
		return this;
	}

	public HtmlParser parseByTagIndex(String tagName, int index) {
		this.html = getElementsByTagIndex(html, tagName, index);
		return this;
	}

	public HtmlParser parseByTagIndexAttr(String tagName, int index, String attribute) {
		this.html = getElementsByTagIndexAttr(html, tagName, index, attribute);
		return this;
	}

	public String[] parseByTag(String tagName) {
		return getElementsByTag(html, tagName);
	}
	
	public String[] parseByTagAttr(String tagName, String attr) {
		return getElementsByTagAttr(html, tagName, attr);
	}

	public HtmlParser parseByClassIndex(String className, int index) {
		this.html = getElementsByClassIndex(html, className, index);
		return this;
	}

	@Override
	public String toString() {
		return html;
	}

	public HtmlParser setHtml(String html) {
		this.html = html;
		return this;
	}

	public VisitResponse getResponse() {
		return response;
	}
	
	public int getStateCode() {
		return response.getStatusCode();
	}
}
