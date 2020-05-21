package cn.mayu.org.yugioh.security.core.base.validatecode;

import java.util.Set;

public interface ValidateCodeProcessorHolder {

	ValidateCodeProcessor getProcessor(String type);
	
	Set<String> getValidateCodeKey();
	
	Set<String> getValidateCode();
}
