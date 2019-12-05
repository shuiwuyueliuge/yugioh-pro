package cn.mayu.yugioh.sync.home.stream;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface LimitOutStream {

	String LIMIT_SAVE_OUTPUT = "limit.save";
	
	String LIMIT_UPDATE_OUTPUT = "limit.update";
	
	@Output(LIMIT_SAVE_OUTPUT)
	MessageChannel limitSaveOutput();
	
	@Output(LIMIT_UPDATE_OUTPUT)
	MessageChannel limitUpdateOutput();
}