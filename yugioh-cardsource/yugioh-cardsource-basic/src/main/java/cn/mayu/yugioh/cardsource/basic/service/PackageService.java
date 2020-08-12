package cn.mayu.yugioh.cardsource.basic.service;

import cn.mayu.yugioh.common.dto.cardsource.PackageData;
import java.util.List;

public interface PackageService {

    void publishPackageDetail(PackageData packageData, Integer sourceType);

    List<PackageData> gainPackageList(Integer sourceType);
}
