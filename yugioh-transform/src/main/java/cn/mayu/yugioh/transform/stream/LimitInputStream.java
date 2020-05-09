package cn.mayu.yugioh.transform.stream;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface LimitInputStream {
	
	String LIMIT_SAVE_INPUT = "limit";
	
	@Input(LIMIT_SAVE_INPUT)
	SubscribableChannel limitSaveInput();
}