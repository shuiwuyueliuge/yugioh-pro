package cn.mayu.yugioh.sync.home.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import static cn.mayu.yugioh.common.core.api.ResultCodeEnum.*;
import cn.mayu.yugioh.common.core.api.ResultCodeEnum;
import cn.mayu.yugioh.common.dto.sync.home.CardProto.CardEntity;
import cn.mayu.yugioh.common.dto.sync.home.LimitProto;
import cn.mayu.yugioh.common.dto.sync.home.SaveResultProto.SaveResultEntity;
import cn.mayu.yugioh.facade.sync.home.SyncHomeService;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class SyncHomeServiceImpl implements SyncHomeService {
	
	@Autowired
	private CardDataService cardDataService;
	
	@Autowired
	private LimitDataService limitDataService;

	@Override
	@PostMapping(value = "/card", consumes = CONSUMES, produces = PRODUCES)
	public SaveResultEntity saveCardInMongo(@RequestBody CardEntity cardEntity) {
		try {
			cardDataService.persistent(cardEntity);
		} catch (Exception e) {
			log.error("persistent card [{}] error [{}]", cardEntity, e);
			return build(FAILURE);
		}
		
		return build(SUCCESS);
	}
	
	@Override
	@PostMapping(value = "/limit", consumes = CONSUMES, produces = PRODUCES)
	public SaveResultEntity saveLimitInMongo(@RequestBody LimitProto.LimitEntity limitEntity) {
		try {
			limitDataService.persistent(limitEntity);
		} catch (Exception e) {
			log.error("persistent limit [{}] error [{}]", limitEntity, e);
			return build(FAILURE);
		}
		
		return build(SUCCESS);
	}
	
	private SaveResultEntity build(ResultCodeEnum codeEnum) {
		return SaveResultEntity.getDefaultInstance().toBuilder().setCode(codeEnum.getCode()).setMsg(codeEnum.getMsg()).build();
	}
}
