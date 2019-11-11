package com.yugioh.start.sync;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import java.time.LocalDateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;
import cn.mayu.yugioh.sync.App;
import cn.mayu.yugioh.sync.entity.IndexEntity;
import cn.mayu.yugioh.sync.repository.CardRepository;
import cn.mayu.yugioh.sync.repository.SyncRecordRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class, webEnvironment = WebEnvironment.NONE)
public class SyncTest {

	@Mock
	CardRepository repository;
	
	@Mock
	SyncRecordRepository record;

	@Test
	public void findData() throws Exception {
		repository.findByModifyTimeGreaterThanEqual(LocalDateTime.of(2019, 5, 11, 11, 11))
				.subscribe(System.out::println);
	}
}
