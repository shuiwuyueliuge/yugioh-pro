package cn.mayu.yugioh.cardsource.stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.support.MessageBuilder;
import cn.mayu.yugioh.common.dto.cardsource.LimitProto.LimitDetail;

@EnableBinding(LimitOutStream.class)
public class LimitPublisher {

	@Autowired
	private LimitOutStream limitPublisher;
	
	public boolean publish(LimitDetail entity) {
		return limitPublisher.LimitOutput().send(MessageBuilder.withPayload(entity.toByteArray()).build());
	}
}