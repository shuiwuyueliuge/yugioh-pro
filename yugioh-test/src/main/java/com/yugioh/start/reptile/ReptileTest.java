package com.yugioh.start.reptile;

import static cn.mayu.yugioh.common.core.util.CrawlUtil.getElementsByTag;

import java.io.FileWriter;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.mayu.yugioh.common.core.util.CrawlUtil.CrawlResponse;
import cn.mayu.yugioh.reptile.ourocg.App;
import cn.mayu.yugioh.reptile.ourocg.manager.CardMetaDataFindManager;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
public class ReptileTest {

	@Autowired
	private CardMetaDataFindManager dataFindManager;
	
	@Test
	public void execTest() {
		IntStream.rangeClosed(1, 1001).boxed().parallel().forEach(num -> {
			try {
				saveInFile(String.format("%s %s", num, dataFindManager.doExec(String.format("https://www.ourocg.cn/card/list-5/%s", num))));
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}
	
	public void saveInFile(final String str) throws Exception {
		try(FileWriter writer = new FileWriter("E:\\ygo\\yugioh\\yugioh-test\\src\\main\\java\\com\\yugioh\\start\\reptile\\bbb.txt", true);) {
			writer.write(str + "\r\n");
		}
	}
	
	@Test
	public void exec() throws Exception {
		CrawlResponse response = getElementsByTag("https://www.ourocg.cn/card/list-5/1", "html", 0);
		System.out.println(response);
	}
}
