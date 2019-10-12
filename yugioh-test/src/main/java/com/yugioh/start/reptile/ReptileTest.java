package com.yugioh.start.reptile;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import cn.mayu.yugioh.reptile.ourocg.App;
import cn.mayu.yugioh.reptile.ourocg.service.OurocgDataService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
public class ReptileTest {

	@Autowired
	private OurocgDataService ourocgDataService;

	
	@Test
	public void execTest() throws Exception {
		int num = 92;
		while(true) {
			System.out.println(num);
			String url = String.format("https://www.ourocg.cn/card/list-5/%s", num);
			try {
				if (num >= ourocgDataService.findOurocgData(url, num)) {
					break;
				}
			} catch (Exception e) {
				e.printStackTrace();
				continue;
			}
			
			num++;
		}
	}
}
