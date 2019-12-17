package cn.mayu.org.yugioh.security.core.base.validatecode;

public interface ValidateCodeGenerator {

	String createAndSend(String key) throws Exception;
}
