package cn.mayu.yugioh.websocket.stream;

import cn.mayu.yugioh.common.dto.websocket.WebSocketMsg;
import cn.mayu.yugioh.websocket.service.ChannelSupervise;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;

import java.util.Map;

@EnableBinding(WebSocketInputStream.class)
public class WebSocketReceiver {

	/**
	 * mq receive {"msg":"123","code":323,"data":{"channelId":"fsdfsa","progress":19}}
	 */
	@StreamListener(WebSocketInputStream.WEB_SOCKET_SAVE_INPUT)
	public void receiveSave(Message<WebSocketMsg> message) {
		WebSocketMsg msg = message.getPayload();
		System.out.println(msg);
		ChannelSupervise.send2One(msg.getChannelId(), msg);
	}
}
