package cn.mayu.yugioh.sync.home.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import static cn.mayu.yugioh.common.core.api.ResultCodeEnum.*;
import cn.mayu.yugioh.common.core.api.ResultCodeEnum;
import cn.mayu.yugioh.facade.sync.home.CardProto;
import cn.mayu.yugioh.facade.sync.home.LimitProto;
import cn.mayu.yugioh.facade.sync.home.CardProto.CardEntity;
import cn.mayu.yugioh.facade.sync.home.LimitDetilProto.LimitDetilEntity;
import cn.mayu.yugioh.facade.sync.home.SaveResultProto.SaveResultEntity;
import cn.mayu.yugioh.facade.sync.home.SyncHomeService;
import cn.mayu.yugioh.sync.home.async.DataTransformer;
import cn.mayu.yugioh.sync.home.entity.CardDataEntity;
import cn.mayu.yugioh.sync.home.entity.IncludeInfo;
import cn.mayu.yugioh.sync.home.entity.LimitEntity;
import cn.mayu.yugioh.sync.home.repository.CardRepository;
import cn.mayu.yugioh.sync.home.repository.LimitRepository;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class SyncHomeServiceImpl implements SyncHomeService {
	
	@Autowired
	private CardRepository cardRepository;
	
	@Autowired
	private LimitRepository limitRepository;
	
	@Autowired
	private DataTransformer dataTransformer;

	@Override
	@PostMapping(value = "/card", consumes = CONSUMES, produces = PRODUCES)
	public SaveResultEntity saveCardInMongo(@RequestBody CardEntity cardEntity) {
		try {
			persistent(cardEntity);
		} catch (Exception e) {
			log.error("persistent card [{}] error [{}]", cardEntity, e);
			return build(FAILURE);
		}
		
		return build(SUCCESS);
	}
	
	@Override
	@PostMapping(value = "/limit", consumes = CONSUMES, produces = PRODUCES)
	public SaveResultEntity saveLimitInMongo(@RequestBody LimitProto.LimitEntity limitEntity) {
 		LimitEntity entity = new LimitEntity();
		entity.setName(limitEntity.getName());
		entity.setCreateTime(LocalDateTime.now());
		entity.setModifyTime(LocalDateTime.now());
		entity.setForbidden(limitEntity.getForbiddenList());
		entity.setLimited(limitEntity.getLimitedList());
		entity.setSemiLimited(limitEntity.getSemiLimitedList());
		LimitEntity saved = limitRepository.findByName(entity.getName()).block();
		if (saved == null) limitRepository.save(entity).subscribe(data -> {
			data.getForbidden().stream().forEach(hashId -> {
				CardDataEntity cardDataEntity = cardRepository.findByHashId(hashId).block();
				LimitDetilEntity.Builder builder = LimitDetilEntity.newBuilder();
				builder.setCardName(cardDataEntity.getName());
				builder.setPsw(cardDataEntity.getPassword());
				builder.setLimitVal(0);
				builder.setTypeVal(cardDataEntity.getTypeVal());
				builder.setName(data.getName());
				dataTransformer.transformLimitSave(builder.build());
			});
			
			data.getLimited().stream().forEach(hashId -> {
				CardDataEntity cardDataEntity = cardRepository.findByHashId(hashId).block();
				LimitDetilEntity.Builder builder = LimitDetilEntity.newBuilder();
				builder.setCardName(cardDataEntity.getName());
				builder.setPsw(cardDataEntity.getPassword());
				builder.setLimitVal(1);
				builder.setTypeVal(cardDataEntity.getTypeVal());
				builder.setName(data.getName());
				dataTransformer.transformLimitSave(builder.build());
			});
			
			data.getSemiLimited().stream().forEach(hashId -> {
				CardDataEntity cardDataEntity = cardRepository.findByHashId(hashId).block();
				LimitDetilEntity.Builder builder = LimitDetilEntity.newBuilder();
				builder.setCardName(cardDataEntity.getName());
				builder.setPsw(cardDataEntity.getPassword());
				builder.setLimitVal(2);
				builder.setTypeVal(cardDataEntity.getTypeVal());
				builder.setName(data.getName());
				dataTransformer.transformLimitSave(builder.build());
			});
		});
		
		return build(SUCCESS);
	}
	
	private SaveResultEntity build(ResultCodeEnum codeEnum) {
		return SaveResultEntity.getDefaultInstance().toBuilder().setCode(codeEnum.getCode()).setMsg(codeEnum.getMsg()).build();
	}
	
	private CardDataEntity initCardDataEntity(CardEntity cardEntity) {
		CardDataEntity cardDataEntity = new CardDataEntity();
		BeanUtils.copyProperties(cardEntity, cardDataEntity);
		cardDataEntity.setModifyTime(LocalDateTime.now());
		cardDataEntity.setCreateTime(LocalDateTime.now());
		List<IncludeInfo> infos = cardEntity.getIncludeInfosList().stream().map(info -> initIncludeInfo(info)).collect(Collectors.toList());
		cardDataEntity.setIncludeInfos(infos);
		return cardDataEntity;
	}
	
	private IncludeInfo initIncludeInfo(CardProto.IncludeInfo info) {
		IncludeInfo includeInfo = new IncludeInfo();
		BeanUtils.copyProperties(info, includeInfo);
		return includeInfo;
	}
	
	private void persistent(CardEntity cardEntity) throws Exception {
		CardDataEntity cardDataEntity = initCardDataEntity(cardEntity);
		CardDataEntity cardInfoEntity = cardRepository.findByHashId(cardDataEntity.getHashId()).block();
		if (cardInfoEntity == null) {
			cardRepository.save(cardDataEntity).subscribe(data -> dataTransformer.transformCardSave(cardEntity));
			return;
		}
		
		if (!cardDataEntity.getVersion().equals(cardInfoEntity.getVersion())) {
			cardDataEntity.setId(cardInfoEntity.getId());
			cardRepository.save(cardDataEntity).subscribe(data -> dataTransformer.transformCardUpdate(cardEntity));
		}
	}
}