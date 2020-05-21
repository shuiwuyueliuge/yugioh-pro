package cn.mayu.yugioh.sync.local.stream;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface LimitInputStream {
	
	String LIMIT_SAVE_INPUT = "limit.save";
	
	@Input(LIMIT_SAVE_INPUT)
	SubscribableChannel limitSaveInput();
}