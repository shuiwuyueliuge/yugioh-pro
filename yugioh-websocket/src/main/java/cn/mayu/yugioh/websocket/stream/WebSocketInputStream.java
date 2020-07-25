package cn.mayu.yugioh.websocket.stream;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface WebSocketInputStream {
	
	String WEB_SOCKET_SAVE_INPUT = "web-socket";
	
	@Input(WEB_SOCKET_SAVE_INPUT)
	SubscribableChannel packageSaveInput();
}