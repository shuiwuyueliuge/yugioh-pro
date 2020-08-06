package cn.mayu.yugioh.cardsource.basic.manager;

import cn.mayu.yugioh.common.dto.cardsource.PackageData;
import java.util.List;

public interface CardSourceStrategy {

    void publishPackageDetail(List<String> packageUrls, String channelId, String subscribe);

    void publishLimitDetail(String LimitUrl);

    List<PackageData> gainPackageList();

    CardSourceEnum getCardSourceType();
}
