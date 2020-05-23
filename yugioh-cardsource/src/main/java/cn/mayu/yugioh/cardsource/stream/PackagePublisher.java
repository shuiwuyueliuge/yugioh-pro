package cn.mayu.yugioh.cardsource.stream;

import cn.mayu.yugioh.common.core.domain.DomainConverterFactory;
import cn.mayu.yugioh.common.dto.cardsource.PackageProto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.support.MessageBuilder;
import cn.mayu.yugioh.common.dto.cardsource.PackageDetail;

@EnableBinding(PackageOutStream.class)
public class PackagePublisher {

	@Autowired
	private PackageOutStream packagePublish;

	private DomainConverterFactory<PackageDetail, PackageProto.PackageDetail> packageDetailConverterFactory;

	{
		packageDetailConverterFactory = new PackageDetailConverterFactory();
	}
	
	public boolean publish(PackageDetail packageDetail) {
		PackageProto.PackageDetail entity = packageDetailConverterFactory.convert(packageDetail);
		return packagePublish.packageDataOutput().send(MessageBuilder.withPayload(entity.toByteArray()).build(), 3000L);
	}
}