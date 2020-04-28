package cn.mayu.yugioh.cardsource.datacenter;

import cn.mayu.yugioh.cardsource.model.CardDetail;

public interface CardDataCenter extends DataCenter {

	CardDetail gainCardDetail(String resources) throws Exception;
	
}
