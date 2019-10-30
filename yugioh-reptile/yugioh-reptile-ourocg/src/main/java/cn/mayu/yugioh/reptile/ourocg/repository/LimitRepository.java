package cn.mayu.yugioh.reptile.ourocg.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import cn.mayu.yugioh.common.mongo.entity.LimitEntity;

public interface LimitRepository extends ReactiveMongoRepository<LimitEntity, String> {

}
