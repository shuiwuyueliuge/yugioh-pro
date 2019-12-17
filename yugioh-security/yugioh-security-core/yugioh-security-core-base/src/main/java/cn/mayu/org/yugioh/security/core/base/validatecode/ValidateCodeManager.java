package cn.mayu.org.yugioh.security.core.base.validatecode;

public interface ValidateCodeManager {

	boolean save(String key, String code);
	
	String get(String key);
}
