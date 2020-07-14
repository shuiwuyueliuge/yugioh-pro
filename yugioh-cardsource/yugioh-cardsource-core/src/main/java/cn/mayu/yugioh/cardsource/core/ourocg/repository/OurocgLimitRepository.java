package cn.mayu.yugioh.cardsource.core.ourocg.repository;

import cn.mayu.yugioh.cardsource.core.ourocg.model.LimitInfo;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface OurocgLimitRepository extends ReactiveMongoRepository<LimitInfo, String> {

}
