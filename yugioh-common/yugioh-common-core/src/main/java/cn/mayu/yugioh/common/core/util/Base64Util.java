package cn.mayu.yugioh.common.core.util;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;

public class Base64Util {

	public static String encode(byte[] content) {
		return Base64.getEncoder().encodeToString(content);
	}

	public static byte[] decoder(String content) {
		return Base64.getDecoder().decode(content);
	}

	public static String decoder2Str(String content) {
		return new String(Base64.getDecoder().decode(content));
	}

	// <img src="data:image/png;base64,....."/>
	public static String urlImg2Base64(String url) throws Exception {
		return encode(urlImg2Byte(url));
	}

	public static byte[] urlImg2Byte(String url) throws Exception {
		HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
		try (InputStream in = conn.getInputStream()) {
			byte[] origin = new byte[128];
			int readLen = 0;
			byte[] content = new byte[readLen];
			while ((readLen = in.read(origin)) != -1) {
				byte[] temp = new byte[readLen + content.length];
				System.arraycopy(content, 0, temp, 0, content.length);
				System.arraycopy(origin, 0, temp, content.length, readLen);
				content = temp;
			}

			return content;
		}
	}
}
