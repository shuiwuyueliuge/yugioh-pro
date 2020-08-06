package cn.mayu.yugioh.cardsource.core.ourocg;

import cn.mayu.yugioh.common.dto.cardsource.PackageData;
import cn.mayu.yugioh.cardsource.basic.manager.CardSourceStrategy;
import org.springframework.boot.CommandLineRunner;
import java.util.List;
import java.util.concurrent.ThreadFactory;

public interface OurocgService extends ThreadFactory, CommandLineRunner, CardSourceStrategy {

    void publishPackageDetail(List<String> packageUrls, String channelId, String subscribe);

    void publishLimitDetail(String LimitUrl);

    List<PackageData> gainPackageList();

    List<String> gainLimitList(String resources);
}
