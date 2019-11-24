package cn.mayu.yugioh.facade.sync.home;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "sync-home")
public interface SyncHomeClient {
	
    @RequestMapping(value = "/card", method = RequestMethod.POST, consumes = "application/x-protobuf", produces = "application/x-protobuf")
    SaveInMongoResultProto.SaveInMongoResultEntity saveCardInMongo(@RequestBody CardProto.CardEntity cardProto);
}