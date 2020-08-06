package cn.mayu.yugioh.cardsource.basic.manager;

import cn.mayu.yugioh.common.dto.cardsource.PackageData;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CardSourceStrategyContext {

    private static Map<CardSourceEnum, CardSourceStrategy> cardSourceStrategyMap;

    @Autowired
    public CardSourceStrategyContext(Set<CardSourceStrategy> cardSources) {
        cardSourceStrategyMap = cardSources.stream().collect(Collectors.toMap(cardSource -> cardSource.getCardSourceType(), Function.identity()));
    }

    public void publishPackageDetail(PackageData packageData, Integer sourceType) {
        getStrategy(sourceType).publishPackageDetail(packageData.getPackageUris(), packageData.getChannelId(), packageData.getSubscribe());
    }

    public void publishLimitDetail(String LimitUrl, Integer sourceType) {
        getStrategy(sourceType).publishLimitDetail(LimitUrl);
    }

    public List<PackageData> gainPackageList(Integer sourceType) {
        return getStrategy(sourceType).gainPackageList();
    }

    private CardSourceStrategy getStrategy(Integer sourceType) {
        return cardSourceStrategyMap.get(CardSourceEnum.search(sourceType));
    }
}
