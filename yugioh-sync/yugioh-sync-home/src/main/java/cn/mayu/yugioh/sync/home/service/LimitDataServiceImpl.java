package cn.mayu.yugioh.sync.home.service;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.mayu.yugioh.common.dto.sync.home.LimitDetilProto.LimitDetilEntity;
import cn.mayu.yugioh.common.dto.sync.home.LimitProto;
import cn.mayu.yugioh.sync.home.async.DataTransformer;
import cn.mayu.yugioh.sync.home.entity.CardDataEntity;
import cn.mayu.yugioh.sync.home.entity.LimitEntity;
import cn.mayu.yugioh.sync.home.repository.CardRepository;
import cn.mayu.yugioh.sync.home.repository.LimitRepository;

@Service
public class LimitDataServiceImpl implements LimitDataService {
	
	@Autowired
	private CardRepository cardRepository;
	
	@Autowired
	private LimitRepository limitRepository;
	
	@Autowired
	private DataTransformer dataTransformer;

	@Override
	public void persistent(LimitProto.LimitEntity limitEntity) throws Exception {
		LimitEntity entity = new LimitEntity();
		entity.setName(limitEntity.getName());
		entity.setCreateTime(LocalDateTime.now());
		entity.setModifyTime(LocalDateTime.now());
		entity.setForbidden(limitEntity.getForbiddenList());
		entity.setLimited(limitEntity.getLimitedList());
		entity.setSemiLimited(limitEntity.getSemiLimitedList());
		LimitEntity saved = limitRepository.findByName(entity.getName()).block();
		if (saved == null) limitRepository.save(entity).subscribe(data -> toLimitDetilEntityAndSave(data));
	}
	
	private void toLimitDetilEntityAndSave(LimitEntity data) {
		String limitName = data.getName();
		limitSave(data.getForbidden(), 0, limitName);
		limitSave(data.getLimited(), 1, limitName);
		limitSave(data.getSemiLimited(), 2, limitName);
	}
	
	private void limitSave(List<String> list, int limitVal, String name) {
		list.stream().forEach(hashId -> {
			CardDataEntity cardDataEntity = cardRepository.findByHashId(hashId).block();
			LimitDetilEntity.Builder builder = LimitDetilEntity.newBuilder();
			builder.setCardName(cardDataEntity.getName());
			builder.setPsw(cardDataEntity.getPassword());
			builder.setLimitVal(limitVal);
			builder.setTypeVal(cardDataEntity.getTypeVal());
			builder.setName(name);
			dataTransformer.transformLimitSave(builder.build());
		});
	}
}
