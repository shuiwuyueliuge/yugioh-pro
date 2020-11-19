package cn.mayu.yugioh.security.core.imagecode;

import cn.mayu.yugioh.security.core.base.config.EhcacheConfig;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

public class ImageCodeConfig {

    @Bean
    @ConditionalOnMissingBean(value = { ImageVerificationCodeManager.class })
    public ImageVerificationCodeManager initImageCodeManager(EhcacheConfig ehcacheConfig) {
        return new DefaultImageVerificationCodeManager(ehcacheConfig);
    }
}
