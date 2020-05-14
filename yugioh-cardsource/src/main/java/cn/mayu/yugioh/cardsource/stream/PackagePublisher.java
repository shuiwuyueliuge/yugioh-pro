package cn.mayu.yugioh.cardsource.stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.support.MessageBuilder;
import cn.mayu.yugioh.common.dto.cardsource.PackageDetail;

@EnableBinding(PackageOutStream.class)
public class PackagePublisher {

	@Autowired
	private PackageOutStream packagePublish;
	
	public boolean publish(PackageDetail entity) {
		return packagePublish.packageDataOutput().send(MessageBuilder.withPayload(entity).build(), 3000L);
	}
}