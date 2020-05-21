package cn.mayu.yugioh.sync.home.stream;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface CardOutStream {

	String CARD_SAVE_OUTPUT = "card.save";
	
	String CARD_UPDATE_OUTPUT = "card.update";
	
	@Output(CARD_SAVE_OUTPUT)
	MessageChannel cardSaveOutput();
	
	@Output(CARD_UPDATE_OUTPUT)
	MessageChannel cardUpdateOutput();
}