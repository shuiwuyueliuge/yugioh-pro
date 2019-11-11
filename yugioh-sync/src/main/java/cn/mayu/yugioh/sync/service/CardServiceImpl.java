package cn.mayu.yugioh.sync.service;

import java.time.LocalDateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import static cn.mayu.yugioh.common.core.util.StringUtil.*;
import cn.mayu.yugioh.common.mongo.entity.CardDataEntity;
import cn.mayu.yugioh.sync.entity.MonsterEntity;
import cn.mayu.yugioh.sync.repository.CardRepository;
import cn.mayu.yugioh.sync.repository.SyncRecordRepository;
import reactor.core.publisher.Flux;

@Service
public class CardServiceImpl implements CardService {

	@Autowired
	private CardRepository repository;

	@Autowired
	private SyncRecordRepository record;
	
	//@Autowired
	//private RedisTemplate<String, IndexEntity> template;
	
	private static final int HASH_ID_SIZE = 6;

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
		if (entity.getTypeVal() == 1) { // monster
			MonsterEntity monsterEntity = new MonsterEntity();
			BeanUtils.copyProperties(entity, monsterEntity);
			monsterEntity.setHashId(generateHashId(HASH_ID_SIZE));
		}

		if (entity.getTypeVal() == 2) {// magic

		}

		if (entity.getTypeVal() == 3) {// trap

		}
	}
}
