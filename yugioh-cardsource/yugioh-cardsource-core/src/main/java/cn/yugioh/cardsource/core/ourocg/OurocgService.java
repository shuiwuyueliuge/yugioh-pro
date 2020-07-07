package cn.yugioh.cardsource.core.ourocg;

import cn.mayu.yugioh.common.dto.cardsource.PackageData;
import cn.yugioh.cardsource.basic.manager.CardSourceStrategy;
import org.springframework.boot.CommandLineRunner;
import java.util.List;
import java.util.concurrent.ThreadFactory;

public interface OurocgService extends Runnable, ThreadFactory, CommandLineRunner, CardSourceStrategy {

    void publishPackageDetail(List<String> packageUrls, Integer priority);

    void publishLimitDetail(String LimitUrl, Integer priority);

    List<PackageData> gainPackageList();

    List<String> gainLimitList(String resources);
}
