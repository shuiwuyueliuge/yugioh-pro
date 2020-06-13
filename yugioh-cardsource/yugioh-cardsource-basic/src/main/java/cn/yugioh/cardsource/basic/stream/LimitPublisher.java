package cn.yugioh.cardsource.basic.stream;

import cn.mayu.yugioh.common.core.domain.DomainConverterFactory;
import cn.mayu.yugioh.common.dto.transform.LimitDetail;
import cn.mayu.yugioh.common.dto.transform.LimitProto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.support.MessageBuilder;

@EnableBinding(LimitOutStream.class)
public class LimitPublisher {

	@Autowired
	private LimitOutStream limitPublisher;

	private DomainConverterFactory<LimitDetail, LimitProto.LimitDetail> limitDetailDomainConverterFactory;

	{
		limitDetailDomainConverterFactory = new LimitInfoConverterFactory();
	}
	
	public boolean publish(LimitDetail limitDetail) {
		LimitProto.LimitDetail entity = limitDetailDomainConverterFactory.convert(limitDetail);
		return limitPublisher.LimitOutput().send(MessageBuilder.withPayload(entity.toByteArray()).build());
	}
}