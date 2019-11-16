package cn.mayu.yugioh.sync.ourocg.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import cn.mayu.yugioh.common.mongo.entity.CardDataEntity;
import reactor.core.publisher.Mono;

public interface CardRepository extends ReactiveMongoRepository<CardDataEntity, String> {

	Mono<CardDataEntity> findByHashId(String hashId);
}
