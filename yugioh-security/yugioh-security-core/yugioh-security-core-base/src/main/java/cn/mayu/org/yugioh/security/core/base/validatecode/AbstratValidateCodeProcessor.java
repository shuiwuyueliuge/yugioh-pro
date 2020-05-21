package cn.mayu.org.yugioh.security.core.base.validatecode;

public abstract class AbstratValidateCodeProcessor implements ValidateCodeProcessor {

    protected ValidateCodeGenerator generator;
	
    protected ValidateCodeManager manager;

	public AbstratValidateCodeProcessor(ValidateCodeGenerator generator, ValidateCodeManager manager) {
		this.generator = generator;
		this.manager = manager;
	}
	
	@Override
	public void sendCode(ValidateCodeContext codeContext) {
		codeContext.setCode(generator.createCode(codeContext));
		codeContext.setKey(codeContext.getRequest().getParameter(getValidateCodeKeyParam()));
		codeContext.setSendRes(generator.send(codeContext));
		manager.save(codeContext);
	}
}
