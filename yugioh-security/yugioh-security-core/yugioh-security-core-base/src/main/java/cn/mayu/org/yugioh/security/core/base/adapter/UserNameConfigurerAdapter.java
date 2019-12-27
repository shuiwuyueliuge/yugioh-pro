package cn.mayu.org.yugioh.security.core.base.adapter;

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

import cn.mayu.org.yugioh.security.core.base.property.ValidateCodeLoginProperty;
import cn.mayu.org.yugioh.security.core.base.validatecode.UserNameAuthenticationFilter;
import cn.mayu.org.yugioh.security.core.base.validatecode.UserNameAuthenticationProvider;
import cn.mayu.org.yugioh.security.core.base.validatecode.ValidateFilter;

public class UserNameConfigurerAdapter extends SecurityConfigurerAdapter<DefaultSecurityFilterChain,HttpSecurity> {

	@Nullable
	private AuthenticationSuccessHandler success;
	
	@Nullable
	private AuthenticationFailureHandler failer;
    
    private UserDetailsService user;
    
    private ValidateCodeLoginProperty validateCodeLoginProperty;

    public UserNameConfigurerAdapter(@Autowired(required = false) AuthenticationSuccessHandler success, @Autowired(required = false) AuthenticationFailureHandler failer,
			UserDetailsService user, ValidateCodeLoginProperty validateCodeLoginProperty) {
		this.success = success;
		this.failer = failer;
		this.user = user;
		this.validateCodeLoginProperty = validateCodeLoginProperty;
	}

	@Override
    public void configure(HttpSecurity builder) throws Exception {
        UserNameAuthenticationFilter userNameCodeAuthenticationFilter = new UserNameAuthenticationFilter(validateCodeLoginProperty.getUserNameProcessingUrl(), validateCodeLoginProperty.getKeyParam());
        userNameCodeAuthenticationFilter.setAuthenticationManager(builder.getSharedObject(AuthenticationManager.class));
        if (success != null) {
        	userNameCodeAuthenticationFilter.setAuthenticationSuccessHandler(success);
        }
        
        if (failer != null) {
        	userNameCodeAuthenticationFilter.setAuthenticationFailureHandler(failer);
        }
        
        UserNameAuthenticationProvider userNameCodeAuthenticationProvider = new UserNameAuthenticationProvider();
        userNameCodeAuthenticationProvider.setUserDetailsService(user);
        builder.authenticationProvider(userNameCodeAuthenticationProvider)
               .addFilterAfter(userNameCodeAuthenticationFilter, ValidateFilter.class)
               .addFilterBefore(userNameCodeAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }
}