package cn.mayu.org.yugioh.security.core.base.validatecode;

public interface ValidateCodeProcessor {

	void sendCode(ValidateCodeContext codeContext);
	
	String getType();
	
	String getValidateCodeKeyParam();
	
	String getValidateCodeParam();
}
