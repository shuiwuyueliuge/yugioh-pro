package cn.mayu.yugioh.security.core.sms;

import cn.mayu.yugioh.security.core.base.property.LoginProperty;
import cn.mayu.yugioh.security.core.base.verificationcode.UserNameAuthenticationFilter;
import cn.mayu.yugioh.security.core.base.verificationcode.UserNameAuthenticationProvider;
import cn.mayu.yugioh.security.core.base.verificationcode.VerificationCodeGenFailureHandler;
import cn.mayu.yugioh.security.core.base.verificationcode.VerificationCodeValidateFailureHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class SmsCodeConfigurerAdapter extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    private SmsCodeProperty smsCodeProperty;

    private LoginProperty loginProperty;

    @Autowired(required = false)
    private VerificationCodeValidateFailureHandler failureHandler;

    @Autowired(required = false)
    private AuthenticationSuccessHandler success;

    @Autowired(required = false)
    private AuthenticationFailureHandler failure;

    private UserDetailsService user;

    @Autowired(required = false)
    private VerificationCodeGenFailureHandler genFailureHandler;

    private SmsVerificationCodeManager codeManager;

    public SmsCodeConfigurerAdapter(SmsCodeProperty smsCodeProperty, LoginProperty loginProperty, UserDetailsService user, SmsVerificationCodeManager codeManager) {
        this.smsCodeProperty = smsCodeProperty;
        this.loginProperty = loginProperty;
        this.user = user;
        this.codeManager = codeManager;
    }

    @Override
    public void configure(HttpSecurity builder) {
        SmsCodeFilter smsCodeFilter = new SmsCodeFilter(smsCodeProperty, loginProperty, codeManager);
        if (failureHandler != null) {
            smsCodeFilter.setFailureHandler(failureHandler);
        }

        if (genFailureHandler != null) {
            smsCodeFilter.setGenFailureHandler(genFailureHandler);
        }

        builder.addFilterBefore(smsCodeFilter, UsernamePasswordAuthenticationFilter.class);
        UserNameAuthenticationFilter userNameCodeAuthenticationFilter = new UserNameAuthenticationFilter(loginProperty.getProcessingUrl(), smsCodeProperty.getSmsCodeKeyParam());
        userNameCodeAuthenticationFilter.setAuthenticationManager(builder.getSharedObject(AuthenticationManager.class));
        if (success != null) {
            userNameCodeAuthenticationFilter.setAuthenticationSuccessHandler(success);
        }

        if (failure != null) {
            userNameCodeAuthenticationFilter.setAuthenticationFailureHandler(failure);
        }

        UserNameAuthenticationProvider userNameCodeAuthenticationProvider = new UserNameAuthenticationProvider();
        userNameCodeAuthenticationProvider.setUserDetailsService(user);
        builder.authenticationProvider(userNameCodeAuthenticationProvider)
                .addFilterBefore(userNameCodeAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
