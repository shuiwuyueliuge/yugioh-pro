package cn.mayu.yugioh.common.facade.transform;

import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class HystrixClientFallbackFactory implements FallbackFactory<CardFacade> {

    @Autowired
    private CardFacadeFallBack cardFacadeFallBack;

    @Override
    public CardFacade create(Throwable cause) {
        log.error("{}", cause);
        return cardFacadeFallBack;
    }
}
