package cn.mayu.yugioh.sync.home.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import cn.mayu.yugioh.common.dto.sync.home.CardProto.CardEntity;
import cn.mayu.yugioh.common.dto.sync.home.LimitProto;
import cn.mayu.yugioh.common.dto.sync.home.ResultProto.ResultEntity;
import cn.mayu.yugioh.facade.sync.home.SyncHomeService;

@RestController
public class SyncHomeServiceImpl implements SyncHomeService {
	
	@Autowired
	private CardDataService cardDataService;
	
	@Autowired
	private LimitDataService limitDataService;

	@Override
	@PostMapping(value = "/card", consumes = CONSUMES, produces = PRODUCES)
	public ResultEntity saveCardInMongo(@RequestBody CardEntity cardEntity) {
		cardDataService.persistent(cardEntity);
		return null;
	}
	
	@Override
	@PostMapping(value = "/limit", consumes = CONSUMES, produces = PRODUCES)
	public ResultEntity saveLimitInMongo(@RequestBody LimitProto.LimitEntity limitEntity) {
		limitDataService.persistent(limitEntity);
		return null;
	}
}
