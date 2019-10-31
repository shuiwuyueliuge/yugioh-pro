package cn.mayu.yugioh.common.core.html;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cn.mayu.yugioh.common.core.util.HtmlUtil;

public class HtmlParser {

	private String html;

	public HtmlParser(String html) {
		this.html = html;
	}
	
	public HtmlParser parseByTagIndex(String tagName, int index) {
		this.html = HtmlUtil.getElementsByTagIndex(html, tagName, index);
		return this;
	}
	
	public List<String> parseByTag(String tagName) {
		String[] objs = HtmlUtil.getElementsByTag(html, tagName);
		return Arrays.asList(objs);
	}
	
	public HtmlParser parseByClassIndex(String className, int index) {
		this.html = HtmlUtil.getElementsByClassIndex(html, className, index);
		return this;
	}
	
	public String getRes() {
		return html;
	}
}
