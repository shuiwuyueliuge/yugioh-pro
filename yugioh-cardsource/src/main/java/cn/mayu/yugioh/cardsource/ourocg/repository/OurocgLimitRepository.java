package cn.mayu.yugioh.cardsource.ourocg.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import cn.mayu.yugioh.cardsource.ourocg.model.LimitInfo;

public interface OurocgLimitRepository extends ReactiveMongoRepository<LimitInfo, String> {

}
