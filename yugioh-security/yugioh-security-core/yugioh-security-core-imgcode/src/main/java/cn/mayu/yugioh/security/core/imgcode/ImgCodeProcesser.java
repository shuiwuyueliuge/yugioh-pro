package cn.mayu.yugioh.security.core.imgcode;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import cn.mayu.org.yugioh.security.core.base.validatecode.AbstratValidateCodeProcessor;
import cn.mayu.org.yugioh.security.core.base.validatecode.ValidateCodeGenerator;
import cn.mayu.org.yugioh.security.core.base.validatecode.ValidateCodeManager;

public class ImgCodeProcesser extends AbstratValidateCodeProcessor {
	
	private final Log logger = LogFactory.getLog(getClass());

	public ImgCodeProcesser(ValidateCodeGenerator generator, ValidateCodeManager manager) {
		super(generator, manager); 
	}

	@Override
	public boolean check(String key, String code) {
		String cache = manager.get(key);
		logger.debug("图片验证码,验证请求code[" + code + "]缓存code:[" + cache + "]");
		if (!cache.equals(code)) {
			return false;
		}
		
		return true;
	}

	@Override
	public String getType() {
		return "img";
	}
}
