package cn.mayu.yugioh.transform.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rabbitmq.client.Channel;
import cn.mayu.yugioh.common.core.util.JsonUtil;
import cn.mayu.yugioh.common.dto.cardsource.LimitDetail;
import cn.mayu.yugioh.transform.config.AsyncConfig;
import cn.mayu.yugioh.transform.entity.ForbiddenEntity;
import cn.mayu.yugioh.transform.repository.ForbiddenRepository;

@Service
public class AsyncReceiverImpl implements AsyncReceiver {
	
	@Autowired
	private ForbiddenRepository forbiddenRepository;

	@Async(AsyncConfig.ASYNC_EXECUTOR_NAME)
	@Override
	@Transactional
	public void receiveLimitData(Message<String> message) throws Exception {
		Channel channel = (Channel) message.getHeaders().get(AmqpHeaders.CHANNEL);
        Long deliveryTag = (Long) message.getHeaders().get(AmqpHeaders.DELIVERY_TAG);
		channel.basicAck(deliveryTag, false);
        String data = message.getPayload();
        LimitDetail limitDetail = JsonUtil.readValue(data, LimitDetail.class);
        List<ForbiddenEntity> entities = new ArrayList<ForbiddenEntity>();
        entities.addAll(limitDetail.getForbidden().stream().map(detail -> {
        	ForbiddenEntity forbiddenEntity = new ForbiddenEntity();
        	forbiddenEntity.setCardName(detail);
        	forbiddenEntity.setLimitVal(0);
        	forbiddenEntity.setLimitTime(limitDetail.getName());
        	return forbiddenEntity;
        }).collect(Collectors.toList()));
        entities.addAll(limitDetail.getLimited().stream().map(detail -> {
        	ForbiddenEntity forbiddenEntity = new ForbiddenEntity();
        	forbiddenEntity.setCardName(detail);
        	forbiddenEntity.setLimitTime(limitDetail.getName());
        	forbiddenEntity.setLimitVal(1);
        	return forbiddenEntity;
        }).collect(Collectors.toList()));
        entities.addAll(limitDetail.getSemiLimited().stream().map(detail -> {
        	ForbiddenEntity forbiddenEntity = new ForbiddenEntity();
        	forbiddenEntity.setCardName(detail);
        	forbiddenEntity.setLimitTime(limitDetail.getName());
        	forbiddenEntity.setLimitVal(2);
        	return forbiddenEntity;
        }).collect(Collectors.toList()));
        forbiddenRepository.saveAll(entities);
	}
}
