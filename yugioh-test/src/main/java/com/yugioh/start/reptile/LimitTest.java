package com.yugioh.start.reptile;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import cn.mayu.yugioh.reptile.ourocg.App;
import cn.mayu.yugioh.reptile.ourocg.service.OurocgDataService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
public class LimitTest {
	
	@Autowired
	OurocgDataService dataService;
	
	@Test
	public void limitDataTest() throws Exception {
		dataService.limitInfoSave("https://www.ourocg.cn/Limit-Latest");
	}
	
	@Test
	public void htmlParse() throws Exception {
		// String limitPage = "https://www.ourocg.cn/Limit-Latest";
		// 禁卡表目录
		Document doc = Jsoup.parse(readHtml());
		doc.getElementsByClass("limit-list sidebar-list").get(0).getElementsByTag("li").stream().forEach(action -> {
			String href = action.getElementsByTag("a").get(0).attr("href");

			Document docu = null;
			try {
				docu = Jsoup.connect(href).get();
			} catch (IOException e) {
				e.printStackTrace();
			}

			System.out.println(docu.getElementsByClass("title").html());
			// 当前禁止卡
			docu.getElementsByTag("tr").stream().map(el -> {
				if (el.getElementsByTag("a").size() > 0) {
					String str = el.getElementsByTag("a").get(0).attr("href");
					str = str.substring(str.lastIndexOf("/") + 1, str.length());
					return str;
				}

				return el.getElementsByTag("th").get(0).html();
			}).forEach(System.out::print);
			System.out.println();
		});
	}

	private String readHtml() throws Exception {
		String html = "";
		try (BufferedReader reader = new BufferedReader(
				new FileReader("E:\\ygo\\yugioh\\yugioh-test\\src\\main\\resources\\NewFile.html"))) {
			String readLine = "";
			while ((readLine = reader.readLine()) != null) {
				html += readLine;
			}

			return html;
		}
	}
}
