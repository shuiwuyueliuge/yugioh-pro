package cn.mayu.yugioh.cardsource.basic.stream;

import cn.mayu.yugioh.common.dto.websocket.WebSocketMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.support.MessageBuilder;

@EnableBinding(WebSocketOutStream.class)
public class WebSocketPublisher {

	@Autowired
	private WebSocketOutStream webSocketOutStream;

	public boolean publishSuccessData(String channelId, String subscribe, Object data) {
		return publish(null, 200, channelId, subscribe, data);
	}

	public boolean publishErrorData(String msg, String channelId, String subscribe, Object data) {
		return publish(msg, 500, channelId, subscribe, data);
	}

	private boolean publish(String msg, Integer code, String channelId, String subscribe, Object data) {
		WebSocketMsg webSocketMsg = new WebSocketMsg();
		webSocketMsg.setChannelId(channelId);
		webSocketMsg.setCode(code);
		webSocketMsg.setData(data);
		webSocketMsg.setMsg(msg);
		webSocketMsg.setSubscribe(subscribe);
		return webSocketOutStream.webSocketOutput().send(MessageBuilder.withPayload(webSocketMsg).build(), 3000L);
	}
}