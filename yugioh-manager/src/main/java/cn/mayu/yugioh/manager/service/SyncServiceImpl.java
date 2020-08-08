package cn.mayu.yugioh.manager.service;

import cn.mayu.yugioh.common.dto.cardsource.LimitData;
import cn.mayu.yugioh.common.dto.cardsource.PackageData;
import cn.mayu.yugioh.common.dto.cardsource.SourceType;
import cn.mayu.yugioh.common.facade.cardsource.CardSourceFacade;
import cn.mayu.yugioh.common.facade.cardsource.LimitFacade;
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
    private LimitFacade limitFacade;

    @Autowired
    private ReactiveRedisTemplate<String, List> packageListRedisTemplate;

    @Override
    public List<PackageData> gainPackageList(Integer sourceType) {
        Optional<List> data = packageListRedisTemplate.opsForValue().get(sourceType + ":package").blockOptional();
        if (data.isPresent()) {
            return data.get();
        }

        List<PackageData> packageList = packageFacade.gainPackageList(sourceType);
        packageListRedisTemplate.opsForValue().set(sourceType + ":package", packageList, Duration.ofDays(1L)).subscribe();
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

    @Override
    public List<LimitData> gainLimitList(Integer sourceType) {
        Optional<List> data = packageListRedisTemplate.opsForValue().get(sourceType + ":limit").blockOptional();
        if (data.isPresent()) {
            return data.get();
        }

        List<LimitData> limitDataList = limitFacade.gainLimitList(sourceType);
        packageListRedisTemplate.opsForValue().set(sourceType + ":limit", limitDataList, Duration.ofDays(1L)).subscribe();
        return limitDataList;
    }

    @Override
    public void publishLimitDetail(LimitData limitData, Integer sourceType) {
        limitFacade.publishLimitDetail(limitData, sourceType);
    }
}
