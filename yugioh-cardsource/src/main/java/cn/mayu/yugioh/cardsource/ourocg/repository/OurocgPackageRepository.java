package cn.mayu.yugioh.cardsource.ourocg.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import cn.mayu.yugioh.cardsource.ourocg.model.OurocgMetaData;

public interface OurocgPackageRepository extends ReactiveMongoRepository<OurocgMetaData, String> {

}
