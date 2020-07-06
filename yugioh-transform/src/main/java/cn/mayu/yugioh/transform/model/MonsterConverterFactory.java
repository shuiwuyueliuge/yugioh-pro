package cn.mayu.yugioh.transform.model;

import cn.mayu.yugioh.common.core.domain.DomainConverterFactory;
import cn.mayu.yugioh.common.dto.transform.CardProto;
import cn.mayu.yugioh.transform.model.entity.MonsterEntity;
import cn.mayu.yugioh.transform.service.IndexService;

public class MonsterConverterFactory implements DomainConverterFactory<CardProto.CardDetail, MonsterEntity> {
	
	private IndexService indexService;
	
	public MonsterConverterFactory(IndexService indexService) {
		this.indexService = indexService;
	}

	@Override
	public MonsterEntity convert(CardProto.CardDetail card) {
		MonsterEntity monsterSaved = new MonsterEntity();
		monsterSaved.setPassword(card.getPassword());
		monsterSaved.setName(card.getName());
		monsterSaved.setNameJa(card.getNameJa());
		monsterSaved.setNameEn(card.getNameEn());
		monsterSaved.setNameNw(card.getNameNw());
		monsterSaved.setLevel(card.getLevel());
		monsterSaved.setAttribute(indexService.findByNameFromCache(2, card.getAttribute()));
		monsterSaved.setRace(indexService.findByNameFromCache(3, card.getRace()));
		monsterSaved.setAtk(card.getAtk().equals("?") ? -1 : Integer.parseInt(card.getAtk()));
		monsterSaved.setPendL(card.getPendL().equals("") ? -1 : Integer.parseInt(card.getPendL()));
		monsterSaved.setPendR(card.getPendR().equals("") ? -1 : Integer.parseInt(card.getPendR()));
		monsterSaved.setLink(card.getLink().equals("") ? -1 : Integer.parseInt(card.getLink()));
		if (card.getDef().equals("")) {
			monsterSaved.setDef(-2);
		} else {
			monsterSaved.setDef(card.getDef().equals("?") ? -1 : Integer.parseInt(card.getDef()));
		}

		return monsterSaved;
	}
}
