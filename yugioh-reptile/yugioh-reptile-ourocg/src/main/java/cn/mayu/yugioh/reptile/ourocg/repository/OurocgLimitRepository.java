package cn.mayu.yugioh.reptile.ourocg.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import cn.mayu.yugioh.reptile.ourocg.model.CardLimitEntity;

public interface OurocgLimitRepository extends ReactiveMongoRepository<CardLimitEntity, String> {

}
