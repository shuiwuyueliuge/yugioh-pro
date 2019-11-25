package cn.mayu.yugioh.sync.home.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import cn.mayu.yugioh.sync.home.entity.LimitEntity;
import reactor.core.publisher.Mono;

public interface LimitRepository extends ReactiveMongoRepository<LimitEntity, String> {

	Mono<LimitEntity> findByName(String name);
}
