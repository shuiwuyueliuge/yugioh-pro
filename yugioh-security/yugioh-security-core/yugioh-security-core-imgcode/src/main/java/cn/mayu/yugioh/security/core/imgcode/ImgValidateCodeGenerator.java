package cn.mayu.yugioh.security.core.imgcode;

import cn.mayu.org.yugioh.security.core.base.validatecode.ValidateCodeGenerator;
import cn.mayu.yugioh.common.core.util.Base64Util;

public class ImgValidateCodeGenerator implements ValidateCodeGenerator {

	@Override
	public String createAndSend(String key) throws Exception {
		String code = "0000";
		byte[] img = ImgCodeHelper.getWriteByteArray(code);
		return Base64Util.encode(img);
	}
}
