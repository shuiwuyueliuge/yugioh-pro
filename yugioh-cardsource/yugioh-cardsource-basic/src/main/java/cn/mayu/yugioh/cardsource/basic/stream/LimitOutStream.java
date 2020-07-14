package cn.mayu.yugioh.cardsource.basic.stream;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface LimitOutStream {
	
	String LIMIT_OUTPUT = "limit";
	
	@Output(LIMIT_OUTPUT)
	MessageChannel LimitOutput();
}