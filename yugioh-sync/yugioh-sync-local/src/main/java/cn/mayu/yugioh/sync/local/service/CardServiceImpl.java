package cn.mayu.yugioh.sync.local.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.mayu.yugioh.facade.sync.home.CardProto.CardEntity;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CardServiceImpl implements CardService {

	@Autowired
	private IndexService indexservice;

	@Autowired
	private MonsterService monsterService;

	@Autowired
	private MagicTrapService magicTrapService;

	@Autowired
	private PackageService packageService;

	@Autowired
	private OtherInfoService otherInfoService;

	@Override
	public void saveCardData(CardEntity entity) {
		indexservice.indexCache();
		try {
			doSave(entity);
		} catch (Exception e) {
			log.error("save card: [{}] error: [{}]", entity, e);
		}
	}

	@Transactional
	private void doSave(CardEntity entity) {
		if (entity.getTypeVal() == 1) {
			monsterService.saveMonsterInfo(entity);
		} else {
			magicTrapService.saveMagicTrapInfo(entity);
		}

		otherInfoService.saveOtherData(entity);
		packageService.savePackageInfo(entity);
	}

	@Override
	public void updateCardData(CardEntity entity) {
		indexservice.indexCache();
		try {
			doUpdate(entity);
		} catch (Exception e) {
			log.error("update card: [{}] error: [{}]", entity, e);
		}
	}

	@Transactional
	private void doUpdate(CardEntity entity) {
		if (entity.getTypeVal() == 1) {
			monsterService.updateMonsterInfo(entity);
		} else {
			magicTrapService.updateMagicTrapInfo(entity);
		}

		otherInfoService.saveOtherData(entity);
		packageService.savePackageInfo(entity);
	}
}
