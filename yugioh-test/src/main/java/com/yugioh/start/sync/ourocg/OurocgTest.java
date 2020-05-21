//package com.yugioh.start.sync.ourocg;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.Mock;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.FilterType;
//import org.springframework.data.redis.core.ReactiveRedisTemplate;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import cn.mayu.yugioh.facade.sync.home.SyncHomeService;
//import cn.mayu.yugioh.sync.ourocg.App;
//import cn.mayu.yugioh.sync.ourocg.service.OurocgDataService;
//import cn.mayu.yugioh.sync.ourocg.service.TaskMemoryService;
//import cn.mayu.yugioh.sync.ourocg.task.OurocgCrawlingTask;
//
//@RunWith(SpringRunner.class)
//@ComponentScan(excludeFilters = {
//		@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = OurocgCrawlingTask.class) 
//		})
//@SpringBootTest(classes = App.class, webEnvironment = WebEnvironment.NONE)
//public class OurocgTest {
//
//	@Autowired
//	TaskMemoryService memoryService;
//
//	@Autowired
//	ReactiveRedisTemplate<String, Long> redisTemplate;
//	
//	@Autowired
//	OurocgDataService ourocgDataService;
//	
//	@Mock
//	SyncHomeService syncHomeService;
//
//	@Test
//	public void incr() throws Exception {
//		String crawlKey = "crawl:ourocg";
//		redisTemplate.delete(crawlKey);
//		long crawlNum = 1L;
//		memoryService.markMemory(crawlKey, crawlNum);
//		assertEquals(memoryService.checkMemory(crawlKey), crawlNum);
//		memoryService.increaseBy(crawlKey);
//		assertEquals(memoryService.checkMemory(crawlKey), 2L);
//		memoryService.remove(crawlKey);
//		assertEquals(memoryService.checkMemory(crawlKey), 0L);
//	}
//	
//	@Test
//	public void skip() throws Exception {
//		ourocgDataService.packageDetilSave();
//	}
//}
