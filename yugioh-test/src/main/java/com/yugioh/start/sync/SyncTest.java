package com.yugioh.start.sync;

//import static org.junit.Assert.assertEquals;
import java.time.LocalDateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;
import cn.mayu.yugioh.sync.local.App;
import cn.mayu.yugioh.sync.local.repository.CardRepository;
import cn.mayu.yugioh.sync.local.repository.SyncRecordRepository;
import cn.mayu.yugioh.sync.local.service.CardService;
import cn.mayu.yugioh.sync.local.service.IndexService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class, webEnvironment = WebEnvironment.NONE)
public class SyncTest {

	@Mock
	CardRepository repository;
	
	@Mock
	SyncRecordRepository record;
	
	@Autowired
	IndexService indexservice;
	
	@Autowired
	CardService cardservice;

	@Test
	public void findData() throws Exception {
		repository.findByModifyTimeGreaterThanEqual(LocalDateTime.of(2019, 5, 11, 11, 11)).subscribe();
	}
	
	@Test
	public void save() throws Exception {
		indexservice.indexCache();
		cardservice.saveCardData();
	}
}
