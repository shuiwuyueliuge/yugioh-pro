package cn.mayu.yugioh.common.core.html;

public interface HtmlTranslater<T> {

	T visitHtml(String url) throws Exception;
}
