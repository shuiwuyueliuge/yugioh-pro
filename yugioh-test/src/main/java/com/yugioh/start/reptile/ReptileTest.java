package com.yugioh.start.reptile;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.stream.IntStream;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import cn.mayu.yugioh.common.core.util.JsonUtil;
import cn.mayu.yugioh.common.core.util.StringUtil;
import cn.mayu.yugioh.reptile.ourocg.App;
import cn.mayu.yugioh.reptile.ourocg.manager.CardMetaDataFindManager;
import cn.mayu.yugioh.reptile.ourocg.model.OurocgMetaData;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
public class ReptileTest {

	@Autowired
	private CardMetaDataFindManager dataFindManager;
	
	@Test
	public void execTest() throws Exception {
		int num = 1;
		while(true) {
			String data = dataFindManager.doExec(String.format("https://www.ourocg.cn/card/list-5/%s", num));
			OurocgMetaData metaData = JsonUtil.readValue(data, OurocgMetaData.class);
			saveInFile(num + "", num);
			if (num > metaData.getMeta().getTotalPage()) {
				break;
			}
			
			num++;
		}
	}
	
	public void saveInFile(final String str, final int num) throws Exception {
		String path = "E:\\ygo\\yugioh\\yugioh-test\\src\\main\\java\\com\\yugioh\\start\\reptile\\" + (num % 5) + ".txt";
		try(FileWriter writer = new FileWriter(path, true);) {
			writer.write(str + "\r\n");
		}
	}
	
	@Test
	public void exec() throws Exception {
		FileReader reader = new FileReader("E:\\ygo\\yugioh\\yugioh-test\\src\\main\\java\\com\\yugioh\\start\\reptile\\bbb.txt");
		try (BufferedReader bufferedReader = new BufferedReader(reader);) {
			String data = bufferedReader.readLine();
			String regEx="[\n`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。， 、？]";
			data = data.replaceAll(regEx, "");
			OurocgMetaData metaData = JsonUtil.readValue(data, OurocgMetaData.class);
			System.out.println(metaData);
		}
	}
	
	public static void main(String[] args) throws Exception {
		FileReader reader = new FileReader("E:\\ygo\\yugioh\\yugioh-test\\src\\main\\java\\com\\yugioh\\start\\reptile\\bbb.txt");
		try (BufferedReader bufferedReader = new BufferedReader(reader);) {
			String data = bufferedReader.readLine();
			OurocgMetaData metaData = JsonUtil.readValue(data, OurocgMetaData.class);
			metaData.getCards().stream().forEach(card -> {
				System.out.println(StringUtil.cardDescLetterFormat(card.getDesc()));
			});
		}
	}
}
