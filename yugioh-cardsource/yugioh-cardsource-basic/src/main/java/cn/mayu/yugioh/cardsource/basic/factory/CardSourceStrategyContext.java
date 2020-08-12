package cn.mayu.yugioh.cardsource.basic.factory;

import org.springframework.beans.factory.annotation.Autowired;
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

    public CardSourceFactory getStrategy(Integer sourceType) {
        return cardSourceMap.get(CardSourceEnum.search(sourceType));
    }
}
