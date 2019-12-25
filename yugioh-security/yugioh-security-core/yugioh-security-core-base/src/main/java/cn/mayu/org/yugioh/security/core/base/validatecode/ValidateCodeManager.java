package cn.mayu.org.yugioh.security.core.base.validatecode;

public interface ValidateCodeManager {

	boolean save(ValidateCodeContext codeContext);
	
	String get(String key);
}
