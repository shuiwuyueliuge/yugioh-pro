package cn.mayu.yugioh.security.core.sms;

import cn.mayu.yugioh.security.core.base.property.LoginProperty;
import cn.mayu.yugioh.security.core.base.verificationcode.VerificationCodeFilter;
import cn.mayu.yugioh.security.core.base.verificationcode.VerificationCodeManager;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SmsCodeFilter extends VerificationCodeFilter {

	private SmsCodeProperty smsCodeProperty;

	private SmsVerificationCodeManager codeManager;

	public SmsCodeFilter(SmsCodeProperty smsCodeProperty,
						 LoginProperty loginProperty,
						 SmsVerificationCodeManager codeManager) {
		super(loginProperty);
		this.smsCodeProperty = smsCodeProperty;
		this.codeManager = codeManager;
	}

	@Override
	protected String getCodeUri() {
		return smsCodeProperty.getSmsCodeUri();
	}

	@Override
	protected String getCodeKeyParam() {
		return smsCodeProperty.getSmsCodeKeyParam();
	}

	@Override
	protected String getCodeValueParam() {
		return smsCodeProperty.getSmsCodeValueParam();
	}

	@Override
	protected VerificationCodeManager getCodeManager() {
		return codeManager;
	}
}
