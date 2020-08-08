package cn.mayu.yugioh.manager.service;

import cn.mayu.yugioh.common.dto.cardsource.LimitData;
import cn.mayu.yugioh.common.dto.cardsource.PackageData;
import cn.mayu.yugioh.common.dto.cardsource.SourceType;
import java.util.List;

public interface SyncService {

    List<PackageData> gainPackageList(Integer sourceType);

    void publishPackageDetail(PackageData packageData, Integer sourceType);

    List<SourceType> getSourceType();

    List<LimitData> gainLimitList(Integer sourceType);

    void publishLimitDetail(LimitData limitData, Integer sourceType);
}
