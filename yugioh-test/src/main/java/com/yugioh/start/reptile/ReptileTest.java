package com.yugioh.start.reptile;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReptileTest {
	
	@Autowired
	private TestRepository tr;

	@Test
	public void test1() {
		TestEntity entity = new TestEntity();
		entity.setAge("123");
		entity.setName("zhangsan");
		//Mono<TestEntity> savedEntity = tr.insert(entity);
		//savedEntity.subscribe(System.out::println);
		
//		tr.findAll().collectList().subscribe(testEntityList -> {
//			for (TestEntity testEntity : testEntityList) {
//				System.out.println(testEntity);
//			}
//		});
		
		//tr.findByNameAndAge("lisi", "28").subscribe(System.out::println);
		tr.findByAgeBetween("12", "133").collectList().subscribe(testEntityList -> {
			for (TestEntity testEntity : testEntityList) {
				System.out.println(testEntity);
			}
		});
	}
}
