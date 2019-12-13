package cn.mayu.yugioh.sync.local.stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import cn.mayu.yugioh.common.dto.sync.home.CardProto;
import cn.mayu.yugioh.common.dto.sync.home.CardProto.CardEntity;
import cn.mayu.yugioh.sync.local.async.MqDataConsomer;

@EnableBinding(CardInputStream.class)
public class CardReceiver {
	
	@Autowired
	private MqDataConsomer dataConsomer;

	@StreamListener(CardInputStream.CARD_SAVE_INPUT)
	public void receiveSave(byte[] data) throws Exception {
		CardEntity entity = CardProto.CardEntity.parseFrom(data);
		dataConsomer.saveCard(entity);
	}
	
	@StreamListener(CardInputStream.CARD_UPDATE_INPUT)
	public void receiveUpdate(byte[] data) throws Exception {
		CardEntity entity = CardProto.CardEntity.parseFrom(data);
		dataConsomer.updateCard(entity);
	}
}
