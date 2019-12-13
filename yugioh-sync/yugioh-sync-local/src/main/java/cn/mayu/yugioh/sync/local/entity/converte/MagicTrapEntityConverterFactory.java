package cn.mayu.yugioh.sync.local.entity.converte;

import static cn.mayu.yugioh.common.core.util.StringUtil.generateHashId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import cn.mayu.yugioh.common.core.domain.AbstractDomainConverterFactory;
import cn.mayu.yugioh.common.dto.sync.home.CardProto.CardEntity;
import cn.mayu.yugioh.sync.local.entity.MagicTrapEntity;
import cn.mayu.yugioh.sync.local.service.IndexService;

@Component
public class MagicTrapEntityConverterFactory extends AbstractDomainConverterFactory<CardEntity, MagicTrapEntity> {

	@Autowired
	private IndexService indexService;
	
	@Override
	protected MagicTrapEntity doConvert(CardEntity entity) {
		MagicTrapEntity magicTrap = new MagicTrapEntity();
		copyProperties(entity, magicTrap);
		magicTrap.setHashId(generateHashId());
		magicTrap.setTypeSt(indexService.findByNameFromCache(5, entity.getTypeSt().split("\\|")[1]));
		return magicTrap;
	}
}
