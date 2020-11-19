package cn.mayu.yugioh.security.core.base.verificationcode;

import javax.servlet.http.HttpServletResponse;

/**
 * generate code
 * send code
 * store code
 */
public interface VerificationCodeManager {

    void genCode(String key, HttpServletResponse response) throws Exception;

    boolean validate(String key, String value);
}
