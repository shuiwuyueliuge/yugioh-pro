package cn.mayu.yugioh.transform.service;

import org.springframework.messaging.Message;

public interface AsyncMqDataService {
	
	void receiveLimitData(Message<byte[]> message) throws Exception;
	
	void receivePackageData(Message<byte[]> message) throws Exception;
}
