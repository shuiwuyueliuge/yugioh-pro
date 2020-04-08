package cn.mayu.yugioh.sync.local.stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Header;

import com.rabbitmq.client.Channel;

import cn.mayu.yugioh.common.dto.sync.home.CardProto;
import cn.mayu.yugioh.common.dto.sync.home.CardProto.CardEntity;
import cn.mayu.yugioh.sync.local.async.MqDataConsomer;

@EnableBinding(CardInputStream.class)
public class CardReceiver {
	
	@Autowired
	private MqDataConsomer dataConsomer;

	@StreamListener(CardInputStream.CARD_SAVE_INPUT)
	public void receiveSave(byte[] data
			//,@Header(AmqpHeaders.CHANNEL) Channel channel,
           // @Header(AmqpHeaders.DELIVERY_TAG) Long deliveryTag
            ) throws Exception {
		CardEntity entity = CardProto.CardEntity.parseFrom(data);
		// channel.basicAck(deliveryTag, false);//手动确认
		dataConsomer.saveCard(entity);
	}
	
	@StreamListener(CardInputStream.CARD_UPDATE_INPUT)
	public void receiveUpdate(byte[] data) throws Exception {
		CardEntity entity = CardProto.CardEntity.parseFrom(data);
		dataConsomer.updateCard(entity);
	}
}
