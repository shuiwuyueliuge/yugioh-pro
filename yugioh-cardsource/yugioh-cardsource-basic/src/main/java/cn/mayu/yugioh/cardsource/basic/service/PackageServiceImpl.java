package cn.mayu.yugioh.cardsource.basic.service;

import cn.mayu.yugioh.cardsource.basic.factory.CardSourceStrategyContext;
import cn.mayu.yugioh.cardsource.basic.factory.PackageFactory;
import cn.mayu.yugioh.common.dto.cardsource.PackageData;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

public class PackageServiceImpl implements PackageService {

    @Autowired
    private CardSourceStrategyContext strategyContext;

    @Override
    public void publishPackageDetail(PackageData data, Integer sourceType) {
        getPackageFactory(sourceType).publishPackageDetail(data.getUrls(), data.getChannelId(), data.getSubscribe());
    }

    @Override
    public List<PackageData> gainPackageList(Integer sourceType) {
        return getPackageFactory(sourceType).gainPackageList();
    }

    private PackageFactory getPackageFactory(Integer sourceType) {
        return strategyContext.getStrategy(sourceType).initPackageFactory();
    }
}
