package cn.mayu.yugioh.websocket.stream;

import cn.mayu.yugioh.common.dto.websocket.PackageWebSocketDTO;
import cn.mayu.yugioh.websocket.model.WebSocketMsg;
import cn.mayu.yugioh.websocket.service.ChannelSupervise;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;

@EnableBinding(WebSocketInputStream.class)
public class WebSocketReceiver {

	/**
	 * mq receive {"msg":"123","code":323,"data":{"channelId":"fsdfsa","progress":19}}
	 */
	@StreamListener(WebSocketInputStream.WEB_SOCKET_SAVE_INPUT)
	public void receiveSave(Message<PackageWebSocketDTO> message) {
		System.out.println(message);
		WebSocketMsg<PackageWebSocketDTO> msg = new WebSocketMsg<>();
		msg.setCode(200);
		msg.setData(message.getPayload());
		ChannelSupervise.send2One(message.getPayload().getChannelId(), msg);
	}
}
