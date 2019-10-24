package cn.mayu.yugioh.reptile.ourocg.model;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import reactor.core.publisher.Mono;

public interface OurocgCardRepository extends ReactiveMongoRepository<CardInfoEntity, String> {

	Mono<CardInfoEntity> findByHashId(String hashId);
}
