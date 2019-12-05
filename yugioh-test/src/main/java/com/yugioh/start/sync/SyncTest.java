package com.yugioh.start.sync;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

//import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

//import cn.mayu.yugioh.common.core.util.Base64Util;
import cn.mayu.yugioh.sync.local.App;
import cn.mayu.yugioh.sync.local.repository.SyncRecordRepository;
import cn.mayu.yugioh.sync.local.service.CardService;
import cn.mayu.yugioh.sync.local.service.IndexService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class, webEnvironment = WebEnvironment.NONE)
public class SyncTest {
	
	@Mock
	SyncRecordRepository record;
	
	@Mock
	IndexService indexservice;
	
	@Mock
	CardService cardservice;

	@Test
	public void findData() throws Exception {
		String s = "2017年11月01日【TCG】 禁限卡表";
		String[] arr = s.replace("【", " ").replace("】 ", " ").split(" ");
		assertEquals(arr.length, 3);
		assertEquals(arr[1], "TCG");
		LocalDate date = LocalDate.parse(arr[0], DateTimeFormatter.ofPattern("yyyy年MM月dd日"));
		assertEquals(date.toString(), "2017-11-01");
	}
}
