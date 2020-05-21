package cn.mayu.yugioh.sync.local.stream;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface CardInputStream {
	
	String CARD_SAVE_INPUT = "card.save";
	
	String CARD_UPDATE_INPUT = "card.update";
	
	@Input(CARD_SAVE_INPUT)
	SubscribableChannel cardSaveInput();
	
	@Input(CARD_UPDATE_INPUT)
	SubscribableChannel cardUpdateInput();
}