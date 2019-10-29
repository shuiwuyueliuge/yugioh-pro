package cn.mayu.yugioh.common.core.html;

public interface HtmlTranslater<T> {

	T visit(String url) throws Exception;
}
