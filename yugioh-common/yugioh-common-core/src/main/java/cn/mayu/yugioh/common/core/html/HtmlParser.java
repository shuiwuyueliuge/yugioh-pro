package cn.mayu.yugioh.common.core.html;

import cn.mayu.yugioh.common.core.util.HtmlUtil;

public class HtmlParser {

	private String html;
	
	private String source;

	public HtmlParser(final String html) {
		this.source = html;
	}
	
	public HtmlParser parserByTag(String tagName, int index) {
		this.html = HtmlUtil.getElementsByTag(source, tagName, index);
		return this;
	}
	
	public HtmlParser parserByClass(String className, int index) {
		this.html = HtmlUtil.getElementsByClass(source, className, index);
		return this;
	}
	
	public HtmlParser reload() {
		this.html = source;
		return this;
	}
}
