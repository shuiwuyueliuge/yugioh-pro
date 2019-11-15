package cn.mayu.yugioh.sync.service;

import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.mayu.yugioh.common.mongo.entity.CardDataEntity;
import cn.mayu.yugioh.sync.repository.CardRepository;
import cn.mayu.yugioh.sync.repository.SyncRecordRepository;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Service
@Slf4j
public class CardServiceImpl implements CardService {

	@Autowired
	private CardRepository repository;

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
	public void saveCardData() throws Exception {
		Flux<CardDataEntity> dataFlux = repository.findByModifyTimeGreaterThanEqual(getLastSyncTime());
		if (dataFlux.count().block() == 0) {
			return;
		}

		dataFlux.subscribe(entity -> doSync(entity));
	}

	private LocalDateTime getLastSyncTime() {
		LocalDateTime lastSyncTime = record.findMaxCreateTime();
		return lastSyncTime == null ? LocalDateTime.of(1970, 1, 1, 1, 1) : lastSyncTime;
	}

	@Transactional
	private void doSync(CardDataEntity entity) {
		try {
			if (entity.getTypeVal() == 1) {
				monsterService.saveMonsterInfo(entity);
			} else {
				magicTrapService.saveMagicTrapInfo(entity);
			}
			
			otherInfoService.saveOtherData(entity);
			packageService.savePackageInfo(entity);
		} catch (Exception e) {
			log.error("save card: [{}] error: [{}]",entity , e);
		}
	}
}
