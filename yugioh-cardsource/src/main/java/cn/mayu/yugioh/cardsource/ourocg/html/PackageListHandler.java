package cn.mayu.yugioh.cardsource.ourocg.html;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.stereotype.Component;
import cn.mayu.yugioh.cardsource.html.DefaultHtmlHandler;
import cn.mayu.yugioh.cardsource.html.HtmlParser;
import cn.mayu.yugioh.cardsource.html.interceptor.HttpStatusCodeInterceptorChain;
import cn.mayu.yugioh.cardsource.html.interceptor.NotFoundStatusCodeInterceptor;
import cn.mayu.yugioh.cardsource.html.interceptor.RetryStatusCodeInterceptor;

@Component
public class PackageListHandler extends DefaultHtmlHandler<List<String>> {

	@Override
	protected List<String> htmlTranslate(HtmlParser parser) {
		String[] res = parser.parseByClassIndex("package-view package-list", 0).parseByTagAttr("a", "href");
		return Stream.of(res).collect(Collectors.toList());
	}

	@Override
	protected void addHttpStatusCodeInterceptor(HttpStatusCodeInterceptorChain chain) {
		chain.addInterceptor(new RetryStatusCodeInterceptor())
	     .addInterceptor(new NotFoundStatusCodeInterceptor());
	}
}
