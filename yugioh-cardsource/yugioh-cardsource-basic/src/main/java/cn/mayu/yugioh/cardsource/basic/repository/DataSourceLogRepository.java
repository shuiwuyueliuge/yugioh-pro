package cn.mayu.yugioh.cardsource.basic.repository;

import cn.mayu.yugioh.cardsource.basic.entity.DataSourceLogEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface DataSourceLogRepository extends ReactiveMongoRepository<DataSourceLogEntity, String> {

}
