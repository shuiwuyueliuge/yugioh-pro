package cn.mayu.yugioh.security.core.sms;

import cn.mayu.yugioh.security.core.base.config.EhcacheConfig;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

public class SmsCodeConfig {

    @Bean
    @ConditionalOnMissingBean(value = { SmsVerificationCodeManager.class })
    public SmsVerificationCodeManager initSmsCodeManager(EhcacheConfig ehcacheConfig) {
        return new DefaultSmsVerificationCodeManager(ehcacheConfig);
    }
}
