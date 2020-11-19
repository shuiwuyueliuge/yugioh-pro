package cn.mayu.yugioh.security.core.base.verificationcode;

import cn.mayu.yugioh.security.core.base.property.LoginProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.web.WebAttributes;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public abstract class VerificationCodeFilter extends OncePerRequestFilter {

    private LoginProperty loginProperty;

    private VerificationCodeValidateFailureHandler failureHandler;

    private VerificationCodeGenFailureHandler genFailureHandler;

    public VerificationCodeFilter(LoginProperty loginProperty) {
        this.loginProperty = loginProperty;
        this.genFailureHandler = (request, response, exception) -> {
            request.setAttribute(WebAttributes.AUTHENTICATION_EXCEPTION, exception);
            request.getRequestDispatcher(loginProperty.getLoginPage()).forward(request, response);
        };

        this.failureHandler = (request, response, exception) -> {
            request.setAttribute(WebAttributes.AUTHENTICATION_EXCEPTION, exception);
            request.getRequestDispatcher(loginProperty.getLoginPage()).forward(request, response);
        };
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        if (request.getRequestURI().equals(getCodeUri())) {
            String key = request.getParameter(getCodeKeyParam());
            // generate code
            try {
                getCodeManager().genCode(key, response);
            } catch (Exception e) {
                genFailureHandler.onGenCodeFailure(request, response, e);
            }

            return;
        }

        if (request.getRequestURI().equals(loginProperty.getProcessingUrl())) {
            String key = request.getParameter(getCodeKeyParam());
            String value = request.getParameter(getCodeValueParam());
            // validate code
            if (getCodeManager().validate(key, value)) {
                filterChain.doFilter(request, response);
                return;
            }

            String errorMsg = String.format("Username:[%s], Key:[%s] -> Code:[%s] invalid", request.getParameter(loginProperty.getUsernameParameter()), key, value);
            failureHandler.onCheckCodeFailure(request, response, new VerificationCodeValidateException(errorMsg));
            return;
        }

        filterChain.doFilter(request, response);
    }

    public void setFailureHandler(VerificationCodeValidateFailureHandler verificationCodeFailureHandler) {
        this.failureHandler = verificationCodeFailureHandler;
    }

    public void setGenFailureHandler(VerificationCodeGenFailureHandler genFailureHandler) {
        this.genFailureHandler = genFailureHandler;
    }

    protected abstract String getCodeUri();

    protected abstract String getCodeKeyParam();

    protected abstract String getCodeValueParam();

    protected abstract VerificationCodeManager getCodeManager();
}
