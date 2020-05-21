package cn.mayu.yugioh.security.core.sms;

import cn.mayu.org.yugioh.security.core.base.validatecode.ValidateCodeContext;
import cn.mayu.org.yugioh.security.core.base.validatecode.ValidateCodeGenerator;
import cn.mayu.yugioh.common.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SmsValidateCodeGenerator implements ValidateCodeGenerator {

	@Override
	public String createCode(ValidateCodeContext codeContext) {
		String code = RandomUtil.validateCoed(4);
		if (log.isDebugEnabled()) {
			log.debug("image validate code: [{}]", code);
		}

		return code;
	}

	@Override
	public String send(ValidateCodeContext codeContext) {
		return "send success";
	}
}
