package cn.mayu.yugioh.websocket.stream;

import cn.mayu.yugioh.common.dto.websocket.WebSocketEvent;
import cn.mayu.yugioh.websocket.service.ChannelSupervise;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;

@Slf4j
@EnableBinding(WebSocketInputStream.class)
public class WebSocketReceiver {

	@StreamListener(WebSocketInputStream.WEB_SOCKET_SAVE_INPUT)
	public void receiveSave(Message<WebSocketEvent> message) {
		WebSocketEvent msg = message.getPayload();
		if (log.isDebugEnabled()) {
			log.debug("websocket event: {}", msg);
		}

		ChannelSupervise.send2One(msg.getSource().getChannelId(), msg);
	}
}
