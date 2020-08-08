package cn.mayu.yugioh.cardsource.basic.stream;

import cn.mayu.yugioh.common.dto.websocket.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.support.MessageBuilder;

@EnableBinding(WebSocketOutStream.class)
public class WebSocketPublisher {

	@Autowired
	private WebSocketOutStream webSocketOutStream;

	private static final long TIMEOUT = 3000L;

	public boolean publishWebsocket(WebSocketSource source, WebSocketResultEnum resultEnum, Object result) {
		WebSocketEvent webSocketEvent = new WebSocketEvent(source, resultEnum.getMsg(), resultEnum.getCode(), result);
		return webSocketOutStream.webSocketOutput().send(MessageBuilder.withPayload(webSocketEvent).build(), TIMEOUT);
	}
}