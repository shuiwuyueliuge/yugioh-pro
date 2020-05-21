package cn.mayu.yugioh.basic.authorize.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public abstract class AuthException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private String msg;
	
	private Exception exception;
	
	public AuthException(Exception exception) {
		this.exception = exception;
		this.msg = "";
	}
	
	public AuthException(String msg) {
		super(msg);
		this.msg = msg;
	}
}
