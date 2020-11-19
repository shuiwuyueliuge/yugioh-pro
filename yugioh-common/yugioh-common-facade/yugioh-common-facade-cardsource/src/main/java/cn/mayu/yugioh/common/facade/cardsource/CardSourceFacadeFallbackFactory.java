package cn.mayu.yugioh.common.facade.cardsource;

import feign.hystrix.FallbackFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class CardSourceFacadeFallbackFactory implements FallbackFactory<CardSourceFacade> {

    @Autowired
    private CardSourceFacadeFallBack sourceFacadeFallBack;

    @Override
    public CardSourceFacade create(Throwable cause) {
        return sourceFacadeFallBack;
    }
}
