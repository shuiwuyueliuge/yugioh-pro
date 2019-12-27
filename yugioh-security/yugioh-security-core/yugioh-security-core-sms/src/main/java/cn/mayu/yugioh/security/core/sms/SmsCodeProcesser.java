package cn.mayu.yugioh.security.core.sms;

import cn.mayu.org.yugioh.security.core.base.validatecode.AbstratValidateCodeProcessor;
import cn.mayu.org.yugioh.security.core.base.validatecode.ValidateCodeGenerator;
import cn.mayu.org.yugioh.security.core.base.validatecode.ValidateCodeManager;

public class SmsCodeProcesser extends AbstratValidateCodeProcessor {

	public SmsCodeProcesser(ValidateCodeGenerator generator, ValidateCodeManager manager) {
		super(generator, manager); 
	}

	@Override
	public String getType() {
		return "sms";
	}

	@Override
	public String getValidateCodeKey() {
		return "mobile";
	}

	@Override
	public String getValidateCode() {
		return "mobilecode";
	}
}
