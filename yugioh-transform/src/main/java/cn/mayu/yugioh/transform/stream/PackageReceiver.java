package cn.mayu.yugioh.transform.stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import cn.mayu.yugioh.transform.manager.AsyncMqDataManager;

@EnableBinding(PackageInputStream.class)
public class PackageReceiver {
	
	@Autowired
	private AsyncMqDataManager asyncMqDataManager; 

	@StreamListener(PackageInputStream.PACKAGE_SAVE_INPUT)
	public void receiveSave(Message<String> message) throws Exception {
		asyncMqDataManager.receivePackageData(message);
	}
}
