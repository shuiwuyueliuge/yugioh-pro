package cn.mayu.yugioh.common.mongo.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import cn.mayu.yugioh.common.mongo.entity.CardLimitEntity;

public interface LimitRepository extends ReactiveMongoRepository<CardLimitEntity, String> {

}
