//package com.yugioh.start.sync;
//
//import static org.junit.Assert.assertEquals;
//import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import com.yugioh.start.App;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = App.class, webEnvironment = WebEnvironment.NONE)
//public class SyncTest {
//
//	@Test
//	public void findData() throws Exception {
//		String s = "2017年11月01日【TCG】 禁限卡表";
//		String[] arr = s.replace("【", " ").replace("】 ", " ").split(" ");
//		assertEquals(arr.length, 3);
//		assertEquals(arr[1], "TCG");
//		LocalDate date = LocalDate.parse(arr[0], DateTimeFormatter.ofPattern("yyyy年MM月dd日"));
//		assertEquals(date.toString(), "2017-11-01");
//	}
//}
