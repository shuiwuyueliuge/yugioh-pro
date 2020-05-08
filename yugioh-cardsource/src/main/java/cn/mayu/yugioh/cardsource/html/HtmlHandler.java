package cn.mayu.yugioh.cardsource.html;

public interface HtmlHandler<T> {

	T handle(String url) throws Exception;
	
}
