package cn.mayu.yugioh.transform.manager;

import org.springframework.messaging.Message;

public interface AsyncMqDataManager {
	
	void receiveLimitData(Message<String> message) throws Exception;
	
	void receivePackageData(Message<String> message) throws Exception;
}
