package com.yugioh.start.sync;

//import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

//import cn.mayu.yugioh.common.core.util.Base64Util;
import cn.mayu.yugioh.common.core.util.FtpHelper;
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
		FtpHelper.builder().host("192.168.15.156").port(21)
		   .user("ygo").psw("ygo").build()
		   .uploadFile("/img/123ss/", "123.txt", "312312".getBytes());
	}
}
