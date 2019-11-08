package com.yugioh.start.sync;

import java.time.LocalDateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import cn.mayu.yugioh.sync.App;
import cn.mayu.yugioh.sync.repository.CardRepository;
import cn.mayu.yugioh.sync.repository.SyncRecordRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
public class SyncTest {

	@Autowired
	CardRepository repository;
	
	@Autowired
	SyncRecordRepository record;

	@Test
	public void findData() throws Exception {
		repository.findByModifyTimeGreaterThanEqual(LocalDateTime.of(2019, 5, 11, 11, 11))
				.subscribe(System.out::println);
	}
	
	@Test
	public void findTime() throws Exception {
		System.out.println(record.findMaxCreateTime());
	}
}
