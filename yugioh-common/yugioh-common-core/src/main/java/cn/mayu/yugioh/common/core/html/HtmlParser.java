package cn.mayu.yugioh.common.core.html;

import cn.mayu.yugioh.common.core.util.HtmlUtil;

public class HtmlParser {

	private String html;

	public HtmlParser(String html) {
		this.setHtml(html);
	}
	
	public HtmlParser parseByTagIndex(String tagName, int index) {
		this.setHtml(HtmlUtil.getElementsByTagIndex(html, tagName, index));
		return this;
	}
	
	public String[] parseByTag(String tagName) {
		return HtmlUtil.getElementsByTag(html, tagName);
	}
	
	public HtmlParser parseByClassIndex(String className, int index) {
		this.setHtml(HtmlUtil.getElementsByClassIndex(html, className, index));
		return this;
	}
	
	public String getRes() {
		return html;
	}

	public HtmlParser setHtml(String html) {
		this.html = html;
		return this;
	}
}
