package cn.mayu.yugioh.sync.repository;

import java.time.LocalDateTime;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import cn.mayu.yugioh.common.mongo.entity.CardDataEntity;
import reactor.core.publisher.Flux;

public interface CardRepository extends ReactiveMongoRepository<CardDataEntity, String> {

	Flux<CardDataEntity> findByModifyTimeGreaterThanEqual(LocalDateTime updateTime);
}
