package cn.mayu.yugioh.common.facade.cardsource;

import cn.mayu.yugioh.common.dto.cardsource.PackageData;
import java.util.ArrayList;
import java.util.List;

public class PackageFacadeFallBack implements PackageFacade {

    @Override
    public void publishPackageDetail(PackageData packageData, Integer sourceType) {

    }

    @Override
    public List<PackageData> gainPackageList(Integer sourceType) {
        return new ArrayList<>();
    }
}
