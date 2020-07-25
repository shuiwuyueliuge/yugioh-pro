package cn.mayu.yugioh.cardsource.basic.stream;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface WebSocketOutStream {
	
	String WEB_SOCKET_OUTPUT = "web-socket";
	
	@Output(WEB_SOCKET_OUTPUT)
	MessageChannel webSocketOutput();
}