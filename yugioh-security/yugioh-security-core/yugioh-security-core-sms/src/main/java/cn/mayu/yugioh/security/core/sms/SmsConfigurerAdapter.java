package cn.mayu.yugioh.security.core.sms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import cn.mayu.org.yugioh.security.core.base.validatecode.UserNameOnlyAuthenticationFilter;
import cn.mayu.org.yugioh.security.core.base.validatecode.UserNameOnlyAuthenticationProvider;
import cn.mayu.org.yugioh.security.core.base.validatecode.ValidateFilter;

public class SmsConfigurerAdapter extends SecurityConfigurerAdapter<DefaultSecurityFilterChain,HttpSecurity> {

	@Nullable
	private AuthenticationSuccessHandler success;
	
	@Nullable
	private AuthenticationFailureHandler failer;
    
    private UserDetailsService user;
    
    private SmsCodeProcesser smsCodeProcesser;
    
    private SmsValidateCodeProperty smsValidateCodeProperty;

    public SmsConfigurerAdapter(@Autowired(required = false) AuthenticationSuccessHandler success, @Autowired(required = false) AuthenticationFailureHandler failer,
			UserDetailsService user, SmsCodeProcesser smsCodeProcesser, SmsValidateCodeProperty smsValidateCodeProperty) {
		this.success = success;
		this.failer = failer;
		this.user = user;
		this.smsCodeProcesser = smsCodeProcesser;
		this.smsValidateCodeProperty = smsValidateCodeProperty;
	}

	@Override
    public void configure(HttpSecurity builder) throws Exception {
        UserNameOnlyAuthenticationFilter userNameCodeAuthenticationFilter = new UserNameOnlyAuthenticationFilter(smsValidateCodeProperty.getProcessingUrl(), smsCodeProcesser.getValidateCodeKeyParam());
        userNameCodeAuthenticationFilter.setAuthenticationManager(builder.getSharedObject(AuthenticationManager.class));
        if (success != null) {
        	userNameCodeAuthenticationFilter.setAuthenticationSuccessHandler(success);
        }
        
        if (failer != null) {
        	userNameCodeAuthenticationFilter.setAuthenticationFailureHandler(failer);
        }
        
        UserNameOnlyAuthenticationProvider userNameCodeAuthenticationProvider = new UserNameOnlyAuthenticationProvider();
        userNameCodeAuthenticationProvider.setUserDetailsService(user);
        builder.authenticationProvider(userNameCodeAuthenticationProvider)
               .addFilterAfter(userNameCodeAuthenticationFilter, ValidateFilter.class)
               .addFilterBefore(userNameCodeAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }
}