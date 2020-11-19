package cn.mayu.yugioh.security.core.sms;

import cn.mayu.yugioh.security.core.base.verificationcode.VerificationCodeManager;
import javax.servlet.http.HttpServletResponse;

public abstract class SmsVerificationCodeManager implements VerificationCodeManager {

    @Override
    public void genCode(String key, HttpServletResponse response) throws Exception {
        doGenCode(key);
    }

    protected abstract void doGenCode(String mobile) throws Exception;
}
