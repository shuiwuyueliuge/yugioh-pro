package cn.mayu.yugioh.cardsource.core.ourocg.repository;

import cn.mayu.yugioh.cardsource.core.ourocg.model.Include;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface OurocgIncludeRepository extends ReactiveMongoRepository<Include, String> {

}
