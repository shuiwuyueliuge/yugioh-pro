package cn.mayu.yugioh.security.core.imgcode;

import cn.mayu.org.yugioh.security.core.base.validatecode.ValidateCodeContext;
import cn.mayu.org.yugioh.security.core.base.validatecode.ValidateCodeGenerator;
import cn.mayu.yugioh.common.core.util.Base64Util;

public class ImgValidateCodeGenerator implements ValidateCodeGenerator {

	@Override
	public String createCode(ValidateCodeContext codeContext) {
		return "0000";
	}

	@Override
	public String send(ValidateCodeContext codeContext) throws Exception {
		byte[] img = ImgCodeHelper.getWriteByteArray(codeContext.getCode());
		return Base64Util.encode(img);
	}

	@Override
	public String getKey(ValidateCodeContext codeContext) {
		return codeContext.getKeyByHeader("key");
	}
}
