package cn.mayu.yugioh.sync.entity.converte;

import static cn.mayu.yugioh.common.core.util.StringUtil.generateHashId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import cn.mayu.yugioh.common.core.domain.AbstractDomainConverterFactory;
import cn.mayu.yugioh.common.mongo.entity.CardDataEntity;
import cn.mayu.yugioh.sync.entity.MonsterEntity;
import cn.mayu.yugioh.sync.service.IndexService;

@Component
public class MonsterEntityConverterFactory extends AbstractDomainConverterFactory<CardDataEntity, MonsterEntity> {
	
	@Autowired
	private IndexService indexService;

	@Override
	protected MonsterEntity doConvert(CardDataEntity entity) {
		MonsterEntity monster = new MonsterEntity();
		copyProperties(entity, monster, "atk", "def", "pendL", "pendR", "link");
		monster.setHashId(generateHashId());
		monster.setAttribute(indexService.findByNameFromCache(2, entity.getAttribute()));
		monster.setRace(indexService.findByNameFromCache(3, entity.getRace()));
		monster.setAtk(propertieFormat(entity.getAtk()));
		monster.setDef(propertieFormat(entity.getDef()));
		monster.setPendL(propertieFormat(entity.getPendL()));
		monster.setPendR(propertieFormat(entity.getPendR()));
		monster.setLink(propertieFormat(entity.getLink()));
		return monster;
	}
	
	private Integer propertieFormat(String content) {
		boolean condition = (content == null || content.indexOf("?") != -1 || content.indexOf("？") != -1);
		return condition ? -1 : Integer.valueOf(content);
	}
}
