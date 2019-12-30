package cn.mayu.yugioh.security.core.social.qq;

public interface QQ {

	QQUserInfo getUserInfo() throws Exception;
	
	String getOpenId() throws Exception;
}
