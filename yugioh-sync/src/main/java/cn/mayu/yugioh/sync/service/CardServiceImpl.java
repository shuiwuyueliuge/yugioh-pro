package cn.mayu.yugioh.sync.service;

import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.mayu.yugioh.common.mongo.entity.CardDataEntity;
import cn.mayu.yugioh.sync.repository.CardRepository;
import cn.mayu.yugioh.sync.repository.SyncRecordRepository;
import reactor.core.publisher.Flux;

@Service
public class CardServiceImpl implements CardService {
	
	@Autowired
	private CardRepository repository;
	
	@Autowired
	private SyncRecordRepository record;

	@Override
	public void saveCardData() throws Exception {
		LocalDateTime lastSyncTime = getLastSyncTime();
		if (lastSyncTime == null) {
			return;
		}
		
		Flux<CardDataEntity> dataFlux = repository.findByModifyTimeGreaterThanEqual(lastSyncTime);
		if (dataFlux.count().block() == 0) {
			return;
		}
		
		doSync(dataFlux);
	}

	private LocalDateTime getLastSyncTime() {
		return record.findMaxCreateTime();
	}
	
	@Transactional
	private void doSync(Flux<CardDataEntity> dataFlux) {
		dataFlux.subscribe(entity -> {
			
		});
	}
}
