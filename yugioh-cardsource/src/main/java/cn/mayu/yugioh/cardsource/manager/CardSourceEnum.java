package cn.mayu.yugioh.cardsource.manager;

import lombok.AllArgsConstructor;

import java.util.stream.Stream;

@AllArgsConstructor
public enum CardSourceEnum {

    OUROCG(0);

    int type;

    public static CardSourceEnum search(Integer type) {
        return Stream.of(values()).filter(cardSource -> cardSource.type == type).findFirst().get();
    }
}
