package cn.mayu.yugioh.security.core.sms;

import cn.mayu.yugioh.security.core.base.config.EhcacheConfig;
import lombok.extern.slf4j.Slf4j;
import org.ehcache.Cache;
import java.util.Random;

@Slf4j
public class DefaultSmsVerificationCodeManager extends SmsVerificationCodeManager {

    private Cache<String, String> cache;

    public DefaultSmsVerificationCodeManager(EhcacheConfig ehcacheConfig) {
        this.cache = ehcacheConfig.getCache();
    }

    @Override
    public void doGenCode(String mobile) {
        // generate image code
        String smsCode = new Random().ints(1000, 9999).findFirst().getAsInt() + "";
        // send image code
        log.info("mobile:[{}],code:[{}]", mobile, smsCode);
        // store image code
        cache.put(mobile, smsCode);
    }

    @Override
    public boolean validate(String mobile, String value) {
        String cached = "";
        synchronized ("") {
            cached = cache.get(mobile);
            cache.remove(mobile);
        }

        return (cached != null && cached.equals(value)) ? true : false;
    }
}
