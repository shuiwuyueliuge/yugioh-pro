package cn.mayu.yugioh.sync.local.entity.converte;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import cn.mayu.yugioh.common.core.domain.AbstractDomainConverterFactory;
import cn.mayu.yugioh.facade.sync.home.CardProto.CardEntity;
import cn.mayu.yugioh.sync.local.config.CardIdThreadLocal;
import cn.mayu.yugioh.sync.local.entity.TypeEntity;
import cn.mayu.yugioh.sync.local.service.IndexService;

@Component
public class TypeEntityConverterFactory extends AbstractDomainConverterFactory<CardEntity, List<TypeEntity>> {

	@Autowired
	private IndexService indexService;
	
	@Autowired
	private CardIdThreadLocal threadLocal;

	@Override
	protected List<TypeEntity> doConvert(CardEntity entity) {
		String[] typeSts = entity.getTypeSt().split("\\|");
		return Stream.of(typeSts)
				     .map(types -> indexService.findByNameFromCache(4, types))
				     .filter(num -> !num.equals(0))
				     .map(num -> getInstance(num))
				     .collect(Collectors.toList());
	}

	private TypeEntity getInstance(Integer typeNum) {
		TypeEntity type = new TypeEntity();
		type.setCardId(threadLocal.getId());
		type.setTypeSt(typeNum);
		return type;
	}
}
