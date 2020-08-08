package cn.mayu.yugioh.cardsource.basic.factory;

import cn.mayu.yugioh.common.dto.cardsource.LimitData;
import java.util.List;

public interface LimitFactory {

    void publishLimitDetail(List<String> LimitUrls, String channelId, String subscribe);

    List<LimitData> gainLimitList();
}
