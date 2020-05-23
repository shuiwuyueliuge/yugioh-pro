package cn.mayu.yugioh.cardsource.datacenter;

import java.util.List;
import cn.mayu.yugioh.common.dto.cardsource.LimitDetail;

public interface LimitCenter extends DataCenter {

	LimitDetail gainLimitDetail(String resources);
	
	List<String> gainLimitList(String resources);
}
