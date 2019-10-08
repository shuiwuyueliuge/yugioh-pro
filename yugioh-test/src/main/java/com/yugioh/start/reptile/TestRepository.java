package com.yugioh.start.reptile;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TestRepository extends ReactiveMongoRepository<TestEntity, String> {

	@Query("{$and:[{'name':'?0'},{'age':'?1'}]}")
	Mono<TestEntity> findByNameAndAge(String name, String age);
	
	Flux<TestEntity> findByAgeBetween(String start, String end);
}
