package cn.mayu.yugioh.reptile.ourocg.model;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ArrRepository extends ReactiveMongoRepository<CardInfoEntity, String> {

}
