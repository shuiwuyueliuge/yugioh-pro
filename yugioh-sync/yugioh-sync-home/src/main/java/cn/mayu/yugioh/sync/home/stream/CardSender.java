package cn.mayu.yugioh.sync.home.stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.support.MessageBuilder;
import cn.mayu.yugioh.common.dto.sync.home.CardProto.CardEntity;

@EnableBinding(CardOutStream.class)
public class CardSender {

	@Autowired
	private CardOutStream output;
	
	public boolean save(CardEntity entity) {
		return output.cardSaveOutput().send(MessageBuilder.withPayload(entity.toByteArray()).build(), 3000L);
	}
	
	public boolean update(CardEntity entity) {
		return output.cardUpdateOutput().send(MessageBuilder.withPayload(entity.toByteArray()).build(), 3000L);
	}
}