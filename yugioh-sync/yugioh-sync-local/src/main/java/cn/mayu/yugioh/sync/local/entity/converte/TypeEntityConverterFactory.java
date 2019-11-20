package cn.mayu.yugioh.sync.local.entity.converte;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import cn.mayu.yugioh.common.core.domain.AbstractDomainConverterFactory;
import cn.mayu.yugioh.common.mongo.entity.CardDataEntity;
import cn.mayu.yugioh.sync.local.entity.TypeEntity;
import cn.mayu.yugioh.sync.local.service.IndexService;

@Component
public class TypeEntityConverterFactory extends AbstractDomainConverterFactory<CardDataEntity, List<TypeEntity>> {

	@Autowired
	private IndexService indexService;

	@Override
	protected List<TypeEntity> doConvert(CardDataEntity entity) {
		String[] typeSts = entity.getTypeSt().split("\\|");
		return Stream.of(typeSts)
				     .map(types -> indexService.findByNameFromCache(4, types))
				     .filter(num -> !num.equals(0))
				     .map(num -> getInstance(Integer.valueOf(entity.getId()), num))
				     .collect(Collectors.toList());
	}

	private TypeEntity getInstance(Integer cardId, Integer typeNum) {
		TypeEntity type = new TypeEntity();
		type.setCardId(cardId);
		type.setTypeSt(typeNum);
		return type;
	}
}
