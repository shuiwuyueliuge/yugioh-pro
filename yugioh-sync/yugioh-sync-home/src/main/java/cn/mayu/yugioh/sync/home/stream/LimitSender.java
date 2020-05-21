package cn.mayu.yugioh.sync.home.stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.support.MessageBuilder;
import cn.mayu.yugioh.common.dto.sync.home.LimitDetilProto.LimitDetilEntity;

@EnableBinding(LimitOutStream.class)
public class LimitSender {

	@Autowired
	private LimitOutStream output;
	
	public boolean save(LimitDetilEntity entity) {
		return output.limitSaveOutput().send(MessageBuilder.withPayload(entity.toByteArray()).build(), 3000L);
	}
}