package cn.mayu.org.yugioh.security.core.base.validatecode;

public interface ValidateCodeProcessorHolder {

	ValidateCodeProcessor getProcessor(String type);
}
