package cn.mayu.yugioh.reptile.ourocg.model;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface OurocgDataRepository extends ReactiveMongoRepository<CardInfoEntity, String> {

}
