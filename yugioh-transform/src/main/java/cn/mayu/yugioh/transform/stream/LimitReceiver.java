package cn.mayu.yugioh.transform.stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import cn.mayu.yugioh.transform.manager.AsyncMqDataManager;

@EnableBinding(LimitInputStream.class)
public class LimitReceiver {
	
	@Autowired
	private AsyncMqDataManager asyncMqDataManager; 

	@StreamListener(LimitInputStream.LIMIT_SAVE_INPUT)
	public void receiveSave(Message<byte[]> message) throws Exception {
		asyncMqDataManager.receiveLimitData(message);
	}
}
