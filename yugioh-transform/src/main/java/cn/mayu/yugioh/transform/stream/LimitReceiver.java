package cn.mayu.yugioh.transform.stream;

import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import com.rabbitmq.client.Channel;

@EnableBinding(LimitInputStream.class)
public class LimitReceiver {

	@StreamListener(LimitInputStream.LIMIT_SAVE_INPUT)
	public void receiveSave(Message<String> message) throws Exception {
		Channel channel = (com.rabbitmq.client.Channel)message.getHeaders().get(AmqpHeaders.CHANNEL);
        Long deliveryTag = (Long) message.getHeaders().get(AmqpHeaders.DELIVERY_TAG);
        channel.basicAck(deliveryTag, false);
        String data = message.getPayload();
		System.out.println(data);
	}
}
