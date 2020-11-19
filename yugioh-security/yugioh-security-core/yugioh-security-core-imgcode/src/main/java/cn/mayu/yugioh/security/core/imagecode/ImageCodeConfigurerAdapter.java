package cn.mayu.yugioh.security.core.imagecode;

import cn.mayu.yugioh.security.core.base.property.LoginProperty;
import cn.mayu.yugioh.security.core.base.verificationcode.VerificationCodeGenFailureHandler;
import cn.mayu.yugioh.security.core.base.verificationcode.VerificationCodeValidateFailureHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class ImageCodeConfigurerAdapter extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

	private ImageCodeProperty imageCodeProperty;

	private LoginProperty loginProperty;

	@Autowired(required = false)
	private VerificationCodeValidateFailureHandler failureHandler;

	@Autowired(required = false)
	private VerificationCodeGenFailureHandler genFailureHandler;

	private ImageVerificationCodeManager codeManager;

	public ImageCodeConfigurerAdapter(ImageCodeProperty imageCodeProperty,
									  LoginProperty loginProperty,
									  ImageVerificationCodeManager codeManager) {
		this.imageCodeProperty = imageCodeProperty;
		this.loginProperty = loginProperty;
		this.codeManager = codeManager;
	}

	@Override
	public void configure(HttpSecurity builder) {
		ImageCodeFilter imageCodeFilter = new ImageCodeFilter(imageCodeProperty, loginProperty, codeManager);
		if (failureHandler != null) {
			imageCodeFilter.setFailureHandler(failureHandler);
		}

		if (genFailureHandler != null) {
			imageCodeFilter.setGenFailureHandler(genFailureHandler);
		}

		builder.addFilterBefore(imageCodeFilter, UsernamePasswordAuthenticationFilter.class);
	}
}
