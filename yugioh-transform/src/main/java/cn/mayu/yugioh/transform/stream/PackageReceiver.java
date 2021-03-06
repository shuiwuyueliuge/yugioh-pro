package cn.mayu.yugioh.transform.stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import cn.mayu.yugioh.transform.service.AsyncMqDataService;

@EnableBinding(PackageInputStream.class)
public class PackageReceiver {
	
	@Autowired
	private AsyncMqDataService asyncMqDataManager;

	@StreamListener(PackageInputStream.PACKAGE_SAVE_INPUT)
	public void receiveSave(Message<byte[]> message) throws Exception {
		asyncMqDataManager.receivePackageData(message);
	}
}
