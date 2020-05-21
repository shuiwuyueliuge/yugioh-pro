package cn.mayu.yugioh.transform.manager;

import org.springframework.messaging.Message;

public interface AsyncMqDataManager {
	
	void receiveLimitData(Message<byte[]> message) throws Exception;
	
	void receivePackageData(Message<byte[]> message) throws Exception;
}
