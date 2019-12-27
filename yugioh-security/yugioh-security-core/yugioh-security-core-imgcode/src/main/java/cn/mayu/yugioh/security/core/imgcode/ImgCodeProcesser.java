package cn.mayu.yugioh.security.core.imgcode;

import cn.mayu.org.yugioh.security.core.base.validatecode.AbstratValidateCodeProcessor;
import cn.mayu.org.yugioh.security.core.base.validatecode.ValidateCodeGenerator;
import cn.mayu.org.yugioh.security.core.base.validatecode.ValidateCodeManager;

public class ImgCodeProcesser extends AbstratValidateCodeProcessor {

	public ImgCodeProcesser(ValidateCodeGenerator generator, ValidateCodeManager manager) {
		super(generator, manager); 
	}

	@Override
	public String getType() {
		return "img";
	}

	@Override
	public String getValidateCodeKey() {
		return "key";
	}

	@Override
	public String getValidateCode() {
		return "imgcode";
	}
}
