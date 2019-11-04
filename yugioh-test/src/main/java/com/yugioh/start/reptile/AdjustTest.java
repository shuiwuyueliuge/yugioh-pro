package com.yugioh.start.reptile;

import java.io.BufferedReader;
import java.io.FileReader;

import org.jsoup.Jsoup;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.yugioh.start.App;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
public class AdjustTest {
	
	String html;
	
	@Before
	public void init() throws Exception {
		html = readHtml();
	}
	
	@Test
	public void htmlParse() throws Exception {
		System.out.println(Jsoup.parse(html).getElementsByClass("wiki").get(0).getElementsByTag("li").get(1).html());
	}

	private String readHtml() throws Exception {
		String html = "";
		try (BufferedReader reader = new BufferedReader(
				new FileReader("E:\\ygo\\yugioh\\yugioh-test\\src\\main\\resources\\package3.html"))) {
			String readLine = "";
			while ((readLine = reader.readLine()) != null) {
				html += readLine;
			}

			return html;
		}
	}
}
