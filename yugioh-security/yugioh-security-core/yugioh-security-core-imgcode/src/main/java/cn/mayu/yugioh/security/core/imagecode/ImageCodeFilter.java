package cn.mayu.yugioh.security.core.imagecode;

import cn.mayu.yugioh.security.core.base.property.LoginProperty;
import cn.mayu.yugioh.security.core.base.verificationcode.VerificationCodeFilter;
import cn.mayu.yugioh.security.core.base.verificationcode.VerificationCodeManager;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ImageCodeFilter extends VerificationCodeFilter {

	private ImageCodeProperty imageCodeProperty;

	private ImageVerificationCodeManager codeManager;

	public ImageCodeFilter(ImageCodeProperty imageCodeProperty, LoginProperty loginProperty, ImageVerificationCodeManager codeManager) {
		super(loginProperty);
		this.imageCodeProperty = imageCodeProperty;
		this.codeManager = codeManager;
	}

	@Override
	protected String getCodeUri() {
		return imageCodeProperty.getImageCodeUri();
	}

	@Override
	protected String getCodeKeyParam() {
		return imageCodeProperty.getImageCodeKeyParam();
	}

	@Override
	protected String getCodeValueParam() {
		return imageCodeProperty.getImageCodeValueParam();
	}

	@Override
	protected VerificationCodeManager getCodeManager() {
		return codeManager;
	}
}
