package cn.mayu.yugioh.sync.local.entity.converte;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import cn.mayu.yugioh.facade.sync.home.CardProto.CardEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import cn.mayu.yugioh.common.core.domain.AbstractDomainConverterFactory;
import cn.mayu.yugioh.sync.local.config.CardIdThreadLocal;
import cn.mayu.yugioh.sync.local.entity.LinkEntity;

@Component
public class LinkEntityConverterFactory extends AbstractDomainConverterFactory<CardEntity, List<LinkEntity>> {

	@Autowired
	private CardIdThreadLocal threadLocal;
	
	@Override
	protected List<LinkEntity> doConvert(CardEntity entity) {
		if (entity.getLink().equals("")) {
			return Collections.emptyList();
		}
		
		String linkArrowStr = entity.getLinkArrow().replace("，", ",");
		String[] linkArrows = linkArrowStr.split(",");
		return Stream.of(linkArrows)
				     .map(arrow -> getInstance(arrow))
				     .collect(Collectors.toList());
	}
	
	private LinkEntity getInstance(String arrow) {
		LinkEntity link = new LinkEntity();
		link.setCardId(threadLocal.getId());
		link.setLinkArrow(Integer.valueOf(arrow));
		return link;
	}
}
