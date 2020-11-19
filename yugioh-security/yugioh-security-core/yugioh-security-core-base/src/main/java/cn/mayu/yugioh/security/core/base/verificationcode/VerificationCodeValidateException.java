package cn.mayu.yugioh.security.core.base.verificationcode;

import org.springframework.security.core.AuthenticationException;

public class VerificationCodeValidateException extends AuthenticationException {

    public VerificationCodeValidateException(String msg) {
        super(msg);
    }
}
