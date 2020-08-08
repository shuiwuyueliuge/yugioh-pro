package cn.mayu.yugioh.cardsource.basic.factory;

import lombok.AllArgsConstructor;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@AllArgsConstructor
public enum CardSourceEnum {

    OUROCG(0);

    int type;

    public static CardSourceEnum search(Integer type) {
        return Stream.of(values()).filter(cardSource -> cardSource.type == type).findFirst().get();
    }

    public static List<CardSourceEnum> sources() {
        return Stream.of(values()).collect(Collectors.toList());
    }

    public Integer getType() {
        return this.type;
    }
}
