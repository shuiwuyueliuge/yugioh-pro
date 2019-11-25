package cn.mayu.yugioh.sync.home.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import cn.mayu.yugioh.sync.home.entity.LimitEntity;

public interface LimitRepository extends ReactiveMongoRepository<LimitEntity, String> {

}
