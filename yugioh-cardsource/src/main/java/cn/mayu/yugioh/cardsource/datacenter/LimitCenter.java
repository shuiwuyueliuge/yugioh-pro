package cn.mayu.yugioh.cardsource.datacenter;

import java.util.List;
import cn.mayu.yugioh.cardsource.model.LimitDetail;

public interface LimitCenter extends DataCenter {

	LimitDetail gainLimitDetail(String resources);
	
	List<String> gainLimitList(String resources);
}
