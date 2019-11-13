package cn.mayu.yugioh.sync.entity.converte;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.stereotype.Component;
import cn.mayu.yugioh.common.core.domain.AbstractDomainConverterFactory;
import cn.mayu.yugioh.common.mongo.entity.CardDataEntity;
import cn.mayu.yugioh.sync.entity.LinkEntity;

@Component
public class LinkEntityConverterFactory extends AbstractDomainConverterFactory<CardDataEntity, List<LinkEntity>> {

	@Override
	protected List<LinkEntity> doConvert(CardDataEntity entity) {
		if (entity.getLink() == null) {
			return Collections.emptyList();
		}
		
		String linkArrowStr = entity.getLinkArrow().replace("，", ",");
		String[] linkArrows = linkArrowStr.split(",");
		return Stream.of(linkArrows)
				     .map(arrow -> getInstance(entity.getId(), arrow))
				     .collect(Collectors.toList());
	}
	
	private LinkEntity getInstance(Integer cardId, String arrow) {
		LinkEntity link = new LinkEntity();
		link.setCardId(cardId);
		link.setLinkArrow(Integer.valueOf(arrow));
		return link;
	}
}
