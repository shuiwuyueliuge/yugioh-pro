package cn.yugioh.cardsource.basic.manager;

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

    public void publishPackageDetail(List<String> packageUrls, Integer priority, Integer sourceType) {
        getStrategy(sourceType).publishPackageDetail(packageUrls, priority);
    }

    public void publishLimitDetail(String LimitUrl, Integer priority, Integer sourceType) {
        getStrategy(sourceType).publishLimitDetail(LimitUrl, priority);
    }

    public List<String> gainPackageList(Integer sourceType) {
        return getStrategy(sourceType).gainPackageList();
    }

    private CardSourceStrategy getStrategy(Integer sourceType) {
        return cardSourceStrategyMap.get(CardSourceEnum.search(sourceType));
    }
}
