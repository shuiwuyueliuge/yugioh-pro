package cn.mayu.yugioh.sync.home.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import cn.mayu.yugioh.sync.home.entity.SyncRecordEntity;

public interface SyncRecordRepository extends ReactiveMongoRepository<SyncRecordEntity, String> {

}
