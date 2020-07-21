package cn.mayu.yugioh.manager.service;

import cn.mayu.yugioh.common.dto.cardsource.PackageData;
import cn.mayu.yugioh.common.dto.cardsource.SourceType;
import cn.mayu.yugioh.common.facade.cardsource.CardSourceFacade;
import cn.mayu.yugioh.common.facade.cardsource.PackageFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.stereotype.Service;
import java.time.Duration;
import java.util.List;
import java.util.Optional;

@Service
public class SyncServiceImpl implements SyncService {

    @Autowired
    private PackageFacade packageFacade;

    @Autowired
    private CardSourceFacade cardSourceFacade;

    @Autowired
    private ReactiveRedisTemplate<Integer, List> packageListRedisTemplate;

    @Override
    public List<PackageData> gainPackageList(Integer sourceType) {
        Optional<List> data = packageListRedisTemplate.opsForValue().get(sourceType).blockOptional();
        if (data.isPresent()) {
            return data.get();
        }

        List<PackageData> packageList = packageFacade.gainPackageList(sourceType);
        packageListRedisTemplate.opsForValue().set(sourceType, packageList, Duration.ofDays(1L)).subscribe();
        return packageList;
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
