package cn.yugioh.cardsource.core.ourocg.repository;

import cn.yugioh.cardsource.core.ourocg.model.OurocgMetaData;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface OurocgPackageRepository extends ReactiveMongoRepository<OurocgMetaData, String> {

}