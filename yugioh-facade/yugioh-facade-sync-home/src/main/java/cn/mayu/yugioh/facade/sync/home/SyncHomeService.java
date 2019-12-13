package cn.mayu.yugioh.facade.sync.home;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import cn.mayu.yugioh.common.dto.sync.home.CardProto;
import cn.mayu.yugioh.common.dto.sync.home.LimitProto;
import cn.mayu.yugioh.common.dto.sync.home.SaveResultProto;

@FeignClient(name = "sync-home", fallback = SyncHomeServiceFallbackFactory.class)
public interface SyncHomeService {
	
	String CONSUMES = "application/x-protobuf";

	String PRODUCES = CONSUMES;
	
    @PostMapping(value = "/card", consumes = CONSUMES, produces = PRODUCES)
    SaveResultProto.SaveResultEntity saveCardInMongo(@RequestBody CardProto.CardEntity cardEntity);
    
    @PostMapping(value = "/limit", consumes = CONSUMES, produces = PRODUCES)
    SaveResultProto.SaveResultEntity saveLimitInMongo(@RequestBody LimitProto.LimitEntity limitEntity);
}