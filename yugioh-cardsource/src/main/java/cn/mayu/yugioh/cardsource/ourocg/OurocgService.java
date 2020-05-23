package cn.mayu.yugioh.cardsource.ourocg;

import cn.mayu.yugioh.cardsource.manager.CardSourceStrategy;
import org.springframework.boot.CommandLineRunner;
import java.util.List;
import java.util.concurrent.ThreadFactory;

public interface OurocgService extends Runnable, ThreadFactory, CommandLineRunner, CardSourceStrategy {

    void publishPackageDetail(List<String> packageUrls, Integer priority);

    void publishLimitDetail(String LimitUrl, Integer priority);

    List<String> gainPackageList();

    List<String> gainLimitList(String resources);
}
