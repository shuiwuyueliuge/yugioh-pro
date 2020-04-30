package cn.mayu.yugioh.cardsource.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import cn.mayu.yugioh.cardsource.ourocg.model.OurocgMetaData;

public interface OurocgRepository extends ReactiveMongoRepository<OurocgMetaData, String> {

}
