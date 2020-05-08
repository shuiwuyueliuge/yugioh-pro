package cn.mayu.yugioh.cardsource.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import cn.mayu.yugioh.cardsource.ourocg.model.Include;

public interface IncludeRepository extends ReactiveMongoRepository<Include, String> {

}
