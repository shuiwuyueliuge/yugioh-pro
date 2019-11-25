package cn.mayu.yugioh.facade.sync.home;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "sync-home")
public interface SyncHomeService {
	
    @PostMapping(value = "/card", consumes = "application/x-protobuf", produces = "application/x-protobuf")
    SaveInMongoResultProto.SaveInMongoResultEntity saveCardInMongo(@RequestBody CardProto.CardEntity cardEntity);
    
    @PostMapping(value = "/limit", consumes = "application/x-protobuf", produces = "application/x-protobuf")
    SaveInMongoResultProto.SaveInMongoResultEntity saveLimitInMongo(@RequestBody LimitProto.LimitEntity limitEntity);
}