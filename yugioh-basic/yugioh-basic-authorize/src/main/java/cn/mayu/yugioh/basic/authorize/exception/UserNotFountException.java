package cn.mayu.yugioh.basic.authorize.exception;

public class UserNotFountException extends AuthException {
	
	private static final long serialVersionUID = 1L;

	public UserNotFountException(Exception exception) {
		super(exception);
	}

	public UserNotFountException(String msg) {
		super(msg);
	}
}
