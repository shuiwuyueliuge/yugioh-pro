package cn.mayu.yugioh.cardsource.manager;

import java.util.List;

public interface CardSourceStrategy {

    void publishPackageDetail(List<String> packageUrls, Integer priority);

    void publishLimitDetail(String LimitUrl, Integer priority);

    List<String> gainPackageList();

    CardSourceEnum getCardSourceType();
}
