package cn.mayu.yugioh.manager.service;

import cn.mayu.yugioh.common.dto.cardsource.PackageData;
import cn.mayu.yugioh.common.dto.cardsource.SourceType;
import cn.mayu.yugioh.common.facade.cardsource.CardSourceFacade;
import cn.mayu.yugioh.common.facade.cardsource.PackageFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SyncServiceImpl implements SyncService {

    @Autowired
    private PackageFacade packageFacade;

    @Autowired
    private CardSourceFacade cardSourceFacade;

    @Override
    public List<PackageData> gainPackageList(Integer sourceType) {
        return packageFacade.gainPackageList(sourceType);
    }

    @Override
    public void publishPackageDetail(PackageData packageData, Integer sourceType) {
        packageFacade.publishPackageDetail(packageData, sourceType);
    }

    @Override
    public List<SourceType> getSourceType() {
        return cardSourceFacade.getSourceType();
    }
}
