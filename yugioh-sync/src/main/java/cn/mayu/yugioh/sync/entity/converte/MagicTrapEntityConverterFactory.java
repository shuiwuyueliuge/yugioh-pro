package cn.mayu.yugioh.sync.entity.converte;

import static cn.mayu.yugioh.common.core.util.StringUtil.generateHashId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import cn.mayu.yugioh.common.core.domain.AbstractDomainConverterFactory;
import cn.mayu.yugioh.common.mongo.entity.CardDataEntity;
import cn.mayu.yugioh.sync.entity.MagicTrapEntity;
import cn.mayu.yugioh.sync.service.IndexService;

@Component
public class MagicTrapEntityConverterFactory extends AbstractDomainConverterFactory<CardDataEntity, MagicTrapEntity> {

	@Autowired
	private IndexService indexService;
	
	@Override
	protected MagicTrapEntity doConvert(CardDataEntity entity) {
		MagicTrapEntity magicTrap = new MagicTrapEntity();
		copyProperties(entity, magicTrap);
		magicTrap.setHashId(generateHashId());
		magicTrap.setTypeSt(indexService.findByNameFromCache(5, entity.getTypeSt().split("\\|")[1]));
		return magicTrap;
	}
}
