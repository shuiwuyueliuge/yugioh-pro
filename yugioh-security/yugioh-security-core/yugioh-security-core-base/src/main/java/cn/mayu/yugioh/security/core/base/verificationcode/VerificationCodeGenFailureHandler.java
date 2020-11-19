package cn.mayu.yugioh.security.core.base.verificationcode;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface VerificationCodeGenFailureHandler {

    void onGenCodeFailure(HttpServletRequest request, HttpServletResponse response,
                            Exception exception) throws IOException, ServletException;
}
