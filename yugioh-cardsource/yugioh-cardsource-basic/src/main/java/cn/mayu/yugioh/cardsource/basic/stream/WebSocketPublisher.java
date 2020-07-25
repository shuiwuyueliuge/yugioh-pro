package cn.mayu.yugioh.cardsource.basic.stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.support.MessageBuilder;

@EnableBinding(WebSocketOutStream.class)
public class WebSocketPublisher {

	@Autowired
	private WebSocketOutStream webSocketOutStream;

	public boolean publish(String msg) {
		return webSocketOutStream.webSocketOutput().send(MessageBuilder.withPayload(msg).build(), 3000L);
	}
}