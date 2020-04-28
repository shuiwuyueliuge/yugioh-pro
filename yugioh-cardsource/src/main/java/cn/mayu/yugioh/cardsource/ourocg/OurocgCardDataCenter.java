package cn.mayu.yugioh.cardsource.ourocg;

import static cn.mayu.yugioh.common.core.util.JsonUtil.readValue;
import org.springframework.stereotype.Component;
import cn.mayu.yugioh.cardsource.datacenter.CardDataCenter;
import cn.mayu.yugioh.cardsource.model.CardDetail;
import cn.mayu.yugioh.cardsource.ourocg.html.CardDataHtmlHandler;
import cn.mayu.yugioh.cardsource.ourocg.model.OurocgMetaData;
import cn.mayu.yugioh.common.core.html.HtmlHandler;

@Component
public class OurocgCardDataCenter implements CardDataCenter {
	
	private String description;
	
	private HtmlHandler<String> cardDataTranslater;
	
	public OurocgCardDataCenter() {
		this.cardDataTranslater = new CardDataHtmlHandler();
		this.description = "https://www.ourocg.cn/card/list-5/1";
	}

	@Override
	public boolean exists() {
		return true;
	}

	@Override
	public String description() {
		return description;
	}

	@Override
	public CardDetail gainCardDetail(String resources) throws Exception {
		String data = cardDataTranslater.handle(resources);
		System.out.println(data);
		OurocgMetaData metaData = readValue(data, OurocgMetaData.class);
		return null;
	}
	
	public static void main(String[] args) throws Exception {
		new OurocgCardDataCenter().gainCardDetail("https://www.ourocg.cn/card/list-5/500");
	}
}
