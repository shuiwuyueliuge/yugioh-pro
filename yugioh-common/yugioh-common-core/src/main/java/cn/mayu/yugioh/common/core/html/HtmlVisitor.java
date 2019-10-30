package cn.mayu.yugioh.common.core.html;

public interface HtmlVisitor<T> {

	T visit(String url) throws Exception;
}
