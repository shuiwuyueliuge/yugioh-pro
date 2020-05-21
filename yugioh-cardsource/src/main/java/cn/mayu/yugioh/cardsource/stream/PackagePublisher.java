package cn.mayu.yugioh.cardsource.stream;

import cn.mayu.yugioh.common.dto.cardsource.PackageProto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.support.MessageBuilder;
import cn.mayu.yugioh.common.dto.cardsource.PackageDetail;

@EnableBinding(PackageOutStream.class)
public class PackagePublisher {

	@Autowired
	private PackageOutStream packagePublish;
	
	public boolean publish(PackageProto.PackageDetail entity) {
		return packagePublish.packageDataOutput().send(MessageBuilder.withPayload(entity.toByteArray()).build(), 3000L);
	}
}