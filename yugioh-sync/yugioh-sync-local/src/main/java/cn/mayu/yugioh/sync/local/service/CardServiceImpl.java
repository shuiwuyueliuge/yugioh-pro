package cn.mayu.yugioh.sync.local.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.mayu.yugioh.facade.sync.home.CardProto.CardEntity;
import cn.mayu.yugioh.sync.local.repository.SyncRecordRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CardServiceImpl implements CardService {
	
	@Autowired
	private IndexService indexservice;

	@Autowired
	private SyncRecordRepository record;

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
		doSave(entity);
	}

	@Transactional
	private void doSave(CardEntity entity) {
		try {
			if (entity.getTypeVal() == 1) {
				monsterService.saveMonsterInfo(entity);
			} else {
				magicTrapService.saveMagicTrapInfo(entity);
			}

			otherInfoService.saveOtherData(entity);
			packageService.savePackageInfo(entity);
		} catch (Exception e) {
			log.error("save card: [{}] error: [{}]", entity, e);
		}
	}

	@Override
	public void updateCardData(CardEntity entity) {
		indexservice.indexCache();
		doUpdate(entity);
	}
	
	@Transactional
	private void doUpdate(CardEntity entity) {
		try {
			if (entity.getTypeVal() == 1) {
				monsterService.updateMonsterInfo(entity);
			} else {
				magicTrapService.updateMagicTrapInfo(entity);
			}

			otherInfoService.saveOtherData(entity);
			packageService.savePackageInfo(entity);
		} catch (Exception e) {
			log.error("update card: [{}] error: [{}]", entity, e);
		}
	}
}
