package cn.mayu.yugioh.transform.service;

import org.springframework.messaging.Message;

public interface AsyncReceiver {
	
	void receiveLimitData(Message<String> message) throws Exception;
	
	void receivePackageData(Message<String> message) throws Exception;
}
