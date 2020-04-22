package cn.mayu.yugioh.sync.local.stream;

import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import com.rabbitmq.client.Channel;
import cn.mayu.yugioh.common.dto.sync.home.CardProto;
import cn.mayu.yugioh.common.dto.sync.home.CardProto.CardEntity;
import cn.mayu.yugioh.sync.local.async.MqDataConsomer;

@EnableBinding(CardInputStream.class)
public class CardReceiver {
	
	@Autowired
	private MqDataConsomer dataConsomer;

	@StreamListener(CardInputStream.CARD_SAVE_INPUT)
	public void receiveSave(Message<byte[]> message) throws Exception {
		Channel channel = (com.rabbitmq.client.Channel)message.getHeaders().get(AmqpHeaders.CHANNEL);
        Long deliveryTag = (Long) message.getHeaders().get(AmqpHeaders.DELIVERY_TAG);
        channel.basicAck(deliveryTag, false);
        byte[] data = message.getPayload();
		CardEntity entity = CardProto.CardEntity.parseFrom(data);
		dataConsomer.saveCard(entity);
	}
	
	@StreamListener(CardInputStream.CARD_UPDATE_INPUT)
	public void receiveUpdate(byte[] data) throws Exception {
		CardEntity entity = CardProto.CardEntity.parseFrom(data);
		dataConsomer.updateCard(entity);
	}
}
