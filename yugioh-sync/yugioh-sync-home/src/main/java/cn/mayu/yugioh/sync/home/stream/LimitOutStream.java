package cn.mayu.yugioh.sync.home.stream;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface LimitOutStream {

	String LIMIT_SAVE_OUTPUT = "limit.save";
	
	@Output(LIMIT_SAVE_OUTPUT)
	MessageChannel limitSaveOutput();
}