package cn.mayu.yugioh.cardsource.basic.factory;

import cn.mayu.yugioh.common.dto.cardsource.BaseData;
import cn.mayu.yugioh.common.dto.cardsource.LimitData;
import cn.mayu.yugioh.common.dto.cardsource.PackageData;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CardSourceStrategyContext {

    private static Map<CardSourceEnum, CardSourceFactory> cardSourceMap;

    @Autowired
    public CardSourceStrategyContext(Set<CardSourceFactory> cardSources) {
        cardSourceMap = cardSources.stream().collect(Collectors.toMap(cardSource -> cardSource.getCardSource(), Function.identity()));
    }

    public void publishPackageDetail(BaseData data, Integer sourceType) {
        getStrategy(sourceType).initPackageFactory().publishPackageDetail(data.getUrls(), data.getChannelId(), data.getSubscribe());
    }

    public List<PackageData> gainPackageList(Integer sourceType) {
        return getStrategy(sourceType).initPackageFactory().gainPackageList();
    }

    public void publishLimitDetail(BaseData data, Integer sourceType) {
        getStrategy(sourceType).initLimitFactory().publishLimitDetail(data.getUrls(), data.getChannelId(), data.getSubscribe());
    }

    public List<LimitData> gainLimitList(Integer sourceType) {
        return getStrategy(sourceType).initLimitFactory().gainLimitList();
    }

    private CardSourceFactory getStrategy(Integer sourceType) {
        return cardSourceMap.get(CardSourceEnum.search(sourceType));
    }
}
