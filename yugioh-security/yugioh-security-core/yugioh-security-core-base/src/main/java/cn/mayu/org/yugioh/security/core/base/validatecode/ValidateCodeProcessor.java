package cn.mayu.org.yugioh.security.core.base.validatecode;

public interface ValidateCodeProcessor {

	boolean sendCode(String key);
	
	boolean check(String key, String code);
}
