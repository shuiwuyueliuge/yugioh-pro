package cn.mayu.org.yugioh.security.core.base.validatecode;

public interface ValidateCodeProcessor {

	void sendCode(ValidateCodeContext codeContext) throws Exception;
	
	boolean check(String key, String code);
	
	String getType();
}
