package cn.mayu.org.yugioh.security.core.base.exception;

import org.springframework.security.core.AuthenticationException;

public class ValidateCodeException extends AuthenticationException {
	
	private static final long serialVersionUID = 1L;

	public ValidateCodeException(String msg) {
		super(msg);
	}
}
