package cn.mayu.yugioh.common.facade.cardsource;

import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class PackageFacadeFallbackFactory implements FallbackFactory<PackageFacade> {

    @Autowired
    private PackageFacadeFallBack packageFacadeFallBack;

    @Override
    public PackageFacade create(Throwable cause) {
        log.error("{}", cause);
        return packageFacadeFallBack;
    }
}
