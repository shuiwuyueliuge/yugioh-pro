package cn.mayu.yugioh.websocket.stream;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;

@EnableBinding(WebSocketInputStream.class)
public class WebSocketReceiver {

	@StreamListener(WebSocketInputStream.WEB_SOCKET_SAVE_INPUT)
	public void receiveSave(Message<String> message) throws Exception {
		System.out.println(message);
	}
}
