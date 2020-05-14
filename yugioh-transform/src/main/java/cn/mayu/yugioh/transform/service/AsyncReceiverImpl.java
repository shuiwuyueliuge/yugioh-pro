package cn.mayu.yugioh.transform.service;

import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.Message;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import com.rabbitmq.client.Channel;
import cn.mayu.yugioh.common.core.util.JsonUtil;
import cn.mayu.yugioh.common.dto.cardsource.LimitDetail;
import cn.mayu.yugioh.transform.config.AsyncConfig;

@Service
public class AsyncReceiverImpl implements AsyncReceiver {

	@Async(AsyncConfig.ASYNC_EXECUTOR_NAME)
	@Override
	public void receiveLimitData(Message<String> message) throws Exception {
		Channel channel = (com.rabbitmq.client.Channel)message.getHeaders().get(AmqpHeaders.CHANNEL);
        Long deliveryTag = (Long) message.getHeaders().get(AmqpHeaders.DELIVERY_TAG);
		channel.basicAck(deliveryTag, false);
        String data = message.getPayload();
        LimitDetail limitDetail = JsonUtil.readValue(data, LimitDetail.class);
	}
}
