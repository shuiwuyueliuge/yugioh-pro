package cn.mayu.yugioh.cardsource.basic.factory;

import cn.mayu.yugioh.common.dto.cardsource.PackageData;
import java.util.List;

public interface PackageFactory {

    void publishPackageDetail(List<String> packageUrls, String channelId, String subscribe);

    List<PackageData> gainPackageList();
}
