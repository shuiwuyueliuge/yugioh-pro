package cn.yugioh.cardsource.basic.datacenter;

import cn.mayu.yugioh.common.dto.cardsource.LimitDetail;

import java.util.List;

public interface LimitCenter extends DataCenter {

	LimitDetail gainLimitDetail(String resources);
	
	List<String> gainLimitList(String resources);
}
