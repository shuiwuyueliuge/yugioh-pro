package cn.mayu.yugioh.sync.home.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import cn.mayu.yugioh.sync.home.entity.SyncRecordEntity;
import cn.mayu.yugioh.sync.home.repository.SyncRecordRepository;

@Service
public class SyncRecordServiceImpl implements SyncRecordService {
	
	@Autowired
	private SyncRecordRepository recordRepository;

	@Override
	@Async
	public void saveRecord(String data, Integer operate) {
		SyncRecordEntity entity = new SyncRecordEntity();
		entity.setData(data);
		entity.setOperate(operate);
		recordRepository.save(entity).subscribe();
	}
}
