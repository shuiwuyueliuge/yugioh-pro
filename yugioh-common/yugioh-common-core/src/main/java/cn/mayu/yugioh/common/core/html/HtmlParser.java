package cn.mayu.yugioh.common.core.html;

import cn.mayu.yugioh.common.core.util.HtmlUtil;

public class HtmlParser {

	private String html;

	private VisitResponse response;

	protected HtmlParser(String url) {
		connUrl(url);
	}

	public HtmlParser connUrl(String url) {
		this.response = HtmlUtil.connect(url);
		this.html = getResponse().getHtml();
		return this;
	}

	public HtmlParser parseByTagIndex(String tagName, int index) {
		this.html = HtmlUtil.getElementsByTagIndex(html, tagName, index);
		return this;
	}

	public HtmlParser parseByTagIndexArr(String tagName, int index, String attribute) {
		this.html = HtmlUtil.getElementsByTagIndexArr(html, tagName, index, attribute);
		return this;
	}

	public String[] parseByTag(String tagName) {
		return HtmlUtil.getElementsByTag(html, tagName);
	}

	public HtmlParser parseByClassIndex(String className, int index) {
		this.html = HtmlUtil.getElementsByClassIndex(html, className, index);
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
}
