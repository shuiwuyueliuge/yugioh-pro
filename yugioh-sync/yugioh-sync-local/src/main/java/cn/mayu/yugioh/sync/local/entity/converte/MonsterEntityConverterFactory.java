package cn.mayu.yugioh.sync.local.entity.converte;

import static cn.mayu.yugioh.common.core.util.StringUtil.generateHashId;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import cn.mayu.yugioh.common.core.domain.DomainConverterFactory;
import cn.mayu.yugioh.common.dto.sync.home.CardProto.CardEntity;
import cn.mayu.yugioh.sync.local.entity.MonsterEntity;
import cn.mayu.yugioh.sync.local.service.IndexService;

@Component
public class MonsterEntityConverterFactory implements DomainConverterFactory<CardEntity, MonsterEntity> {
	
	@Autowired
	private IndexService indexService;

	@Override
	public MonsterEntity convert(CardEntity entity) {
		MonsterEntity monster = new MonsterEntity();
		BeanUtils.copyProperties(entity, monster, "atk", "def", "pendL", "pendR", "link");
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
		boolean condition = (content.equals("") || content.indexOf("?") != -1 || content.indexOf("？") != -1);
		return condition ? -1 : Integer.valueOf(content);
	}
}
