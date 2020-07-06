package cn.yugioh.cardsource.basic.manager;

import java.util.List;

public interface CardSourceStrategy {

    void publishPackageDetail(List<String> packageUrls, Integer priority);

    void publishLimitDetail(String LimitUrl, Integer priority);

    List<String> gainPackageList();

    CardSourceEnum getCardSourceType();
}
