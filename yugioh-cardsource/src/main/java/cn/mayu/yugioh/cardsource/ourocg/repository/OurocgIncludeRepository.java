package cn.mayu.yugioh.cardsource.ourocg.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import cn.mayu.yugioh.cardsource.ourocg.model.Include;

public interface OurocgIncludeRepository extends ReactiveMongoRepository<Include, String> {

}
