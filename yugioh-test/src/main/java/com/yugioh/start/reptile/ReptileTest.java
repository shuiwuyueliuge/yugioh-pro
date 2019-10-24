package com.yugioh.start.reptile;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import java.io.File;
//import static org.junit.Assert.assertNull;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import cn.mayu.yugioh.reptile.ourocg.App;
import cn.mayu.yugioh.reptile.ourocg.model.OurocgCardRepository;
import cn.mayu.yugioh.reptile.ourocg.service.OurocgDataService;
import static cn.mayu.yugioh.common.core.util.FileUtil.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
public class ReptileTest {

	@Autowired
	private OurocgDataService ourocgDataService;

	@Autowired
	OurocgCardRepository repository;
	
	@Test
	public void findOurocgDataTest() throws Exception {
		int num = 1;
		while(true) {
			String url = String.format("https://www.ourocg.cn/card/list-5/%s", num);
			try {
				if (!ourocgDataService.ourocgDataInFile(url)) {
					break;
				}
			} catch (Exception e) {
				e.printStackTrace();
				continue;
			}
			
			num++;
		}
	}
	
	@Test
	public void findPackageDetil() throws Exception {
		ourocgDataService.packageDetilSave();
	}
	
	@Test
	public void fileTest() throws Exception {
		File file = new File(genTodayFileName());
		assertFalse(file.exists());
	}
	
	String base = "0123456789qwertyuiopasdfghjklzxcvbnmQWERTYUIOPLKJHGFDSAZXCVBNM";
	
	@Test
	public void hashTest() throws Exception {
		int start = 1, total = 70000, size = 6;
		Set<String> continer = IntStream.rangeClosed(start, total).boxed().map(num -> generateHashId(size)).collect(Collectors.toSet());
		assertEquals(continer.size(), total);
	}
	
	private String generateHashId(int size) {
		StringBuilder builder = new StringBuilder();
		Random random = new Random();
		IntStream.rangeClosed(1, size).boxed().forEach(num -> builder.append(base.charAt(random.nextInt(base.length() - 1))));
		return builder.toString();
	}
}
