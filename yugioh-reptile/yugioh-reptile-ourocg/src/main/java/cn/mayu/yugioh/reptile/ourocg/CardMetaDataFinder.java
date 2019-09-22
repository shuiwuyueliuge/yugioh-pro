package cn.mayu.yugioh.reptile.ourocg;

import java.io.FileOutputStream;
import java.util.concurrent.TimeUnit;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.jsoup.Connection.Response;

public class CardMetaDataFinder {

	static Logger log = LoggerFactory.getLogger(CardMetaDataFinder.class);
	
	private static final String AGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko)  Chrome/56.0.2924.87 Safari/537.36";

	private static final String BASE_ULR = "https://www.ourocg.cn/card/list-5/%s";
	
	private static final String RETRY_AFTER = "retry-after";
	
	public static void exec(int num) {
		try {
			Connection connection = Jsoup.connect(String.format(BASE_ULR, num)).ignoreContentType(false).userAgent(AGENT).ignoreHttpErrors(true);
			Response response = connection.execute();
			if (isSleep(response)) {
				exec(num);
				return;
			}
			
			String s = response.parse().getElementsByTag("script").get(2).toString().replace(" ", "").replace("\n", "");
			saveInFile(num, s);
		} catch (Exception e) {
			log.error("find card data error [{}]", e);
		}
	}
	
	private static boolean isSleep(Response response) throws Exception {
		if (response.statusCode() == 200) {
			return false;
		}
		
		if (response.statusCode() == 429 || response.hasHeader(RETRY_AFTER)) {
			log.info("connect [{}] status code is [{}] and retry-after is [{}]", response.url(), response.statusCode(), response.header(RETRY_AFTER));
			long time = Long.parseLong(response.header(RETRY_AFTER));
			if (time > 0) {
				TimeUnit.SECONDS.sleep(time);
			}
			
			return true;
		}
		
		log.error("connect [{}] status code is [{}]", response.url(), response.statusCode());
		return false;
	}
	
	private static void saveInFile(int num, String data) throws Exception {
		FileOutputStream out = null;
		try {
			out = new FileOutputStream("E:\\eclipse\\test1\\src\\test\\java\\com\\app\\aaaa.txt", true);
			out.write((num + " " + data + "\r\n").getBytes());
		} finally {
			if (out != null) out.close();
		}
	}
}
