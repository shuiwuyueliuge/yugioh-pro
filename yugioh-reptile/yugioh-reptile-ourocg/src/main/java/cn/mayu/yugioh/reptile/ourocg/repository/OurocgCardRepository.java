package cn.mayu.yugioh.reptile.ourocg.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import cn.mayu.yugioh.reptile.ourocg.model.CardInfoEntity;
import reactor.core.publisher.Mono;

public interface OurocgCardRepository extends ReactiveMongoRepository<CardInfoEntity, String> {

	Mono<CardInfoEntity> findByHashId(String hashId);
}
