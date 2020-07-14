package cn.mayu.yugioh.cardsource.basic.datacenter;

import cn.mayu.yugioh.common.dto.transform.LimitDetail;

import java.util.List;

public interface LimitCenter extends DataCenter {

	LimitDetail gainLimitDetail(String resources);
	
	List<String> gainLimitList(String resources);
}
