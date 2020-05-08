package cn.mayu.yugioh.cardsource.ourocg.html;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import cn.mayu.yugioh.cardsource.html.DefaultHtmlHandler;
import cn.mayu.yugioh.cardsource.html.HtmlParser;
import cn.mayu.yugioh.cardsource.html.interceptor.HttpStatusCodeInterceptorChain;
import cn.mayu.yugioh.cardsource.html.interceptor.NotFoundStatusCodeInterceptor;
import cn.mayu.yugioh.cardsource.html.interceptor.RetryStatusCodeInterceptor;
import cn.mayu.yugioh.cardsource.ourocg.model.Include;
import cn.mayu.yugioh.cardsource.ourocg.model.IncludeInfo;

@Component
public class IncludeInfoHandler extends DefaultHtmlHandler<Include> {
	
	@Override
	protected Include htmlTranslate(HtmlParser parser) {
		String[] s = parser.parseByTag("template");
		String html = parser.toString();
		String[] res = parser.parseByTag("td");
		List<IncludeInfo> infos = new ArrayList<IncludeInfo>();
		collectToList(infos, res, parser);
		String adjust = parseAdjust(parser.setHtml(html));
		Include cd = new Include();
		cd.setAdjust(adjust);
		cd.setIncludeInfos(infos);
		cd.setCardName(s[0]);
		return cd;
	}
	
	private void collectToList(List<IncludeInfo> infos, String[] res, HtmlParser parser) {
		for (int i = 3; i <= res.length; i += 3) {
			IncludeInfo builder = new IncludeInfo();
			String number = res[i - 2];
			if (number.indexOf("-") != -1) {
				builder.setShortName(number.substring(0, number.indexOf("-")));
			}
			
			builder.setPackName(parser.setHtml(res[i - 3]).parseByTagIndex("a", 0).toString());
			builder.setHref(parser.setHtml(res[i - 3]).parseByTagAttr("a", "href")[0]);
			builder.setSellTime(parser.setHtml(res[i - 3]).parseByTagIndex("small", 0).toString());
			builder.setNumber(number);
			builder.setRare(res[i - 1]);
			infos.add(builder);
		}
	}
	
	private String parseAdjust(HtmlParser parser) {
		try {
			String adjust = parser.parseByClassIndex("wiki", 0).parseByTagIndex("li", 1).toString();
		    return adjust.equals("　　　　") ? "" : adjust;
		} catch (Exception e) {
			return "";
		}
	}

	@Override
	protected void addHttpStatusCodeInterceptor(HttpStatusCodeInterceptorChain chain) {
		chain.addInterceptor(new RetryStatusCodeInterceptor())
	     .addInterceptor(new NotFoundStatusCodeInterceptor());
	}
}
