package cn.mayu.org.yugioh.security.core.base.validatecode;

public interface ValidateCodeGenerator {

	String createCode(ValidateCodeContext codeContext);
	
	String send(ValidateCodeContext codeContext);
}
