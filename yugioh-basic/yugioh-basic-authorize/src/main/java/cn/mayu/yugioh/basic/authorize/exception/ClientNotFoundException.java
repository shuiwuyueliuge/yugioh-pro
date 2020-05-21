package cn.mayu.yugioh.basic.authorize.exception;

public class ClientNotFoundException extends AuthException {

	private static final long serialVersionUID = 1L;
	
	public ClientNotFoundException(String msg) {
		super(msg);
	}
}
