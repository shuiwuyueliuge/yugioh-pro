package cn.mayu.yugioh.transform.stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import cn.mayu.yugioh.transform.service.AsyncMqDataService;

@EnableBinding(LimitInputStream.class)
public class LimitReceiver {
	
	@Autowired
	private AsyncMqDataService asyncMqDataManager;

	@StreamListener(LimitInputStream.LIMIT_SAVE_INPUT)
	public void receiveSave(Message<byte[]> message) throws Exception {
		asyncMqDataManager.receiveLimitData(message);
	}
}
