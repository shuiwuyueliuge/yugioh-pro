package cn.mayu.yugioh.cardsource.basic.manager;

import cn.mayu.yugioh.common.dto.cardsource.PackageData;
import java.util.List;

public interface CardSourceStrategy {

    void publishPackageDetail(List<String> packageUrls, Integer priority);

    void publishLimitDetail(String LimitUrl, Integer priority);

    List<PackageData> gainPackageList();

    CardSourceEnum getCardSourceType();
}
