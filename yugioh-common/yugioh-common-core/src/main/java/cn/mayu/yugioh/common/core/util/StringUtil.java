package cn.mayu.yugioh.common.core.util;

import java.util.Random;
import java.util.stream.IntStream;

public class StringUtil {
	
	private static final String BASE = "0123456789qwertyuiopasdfghjklzxcvbnmQWERTYUIOPLKJHGFDSAZXCVBNM";
	
	private static final int HASH_ID_SIZE = 8;
	
	public static String generateHashId() {
		StringBuilder builder = new StringBuilder();
		Random random = new Random();
		IntStream.rangeClosed(1, HASH_ID_SIZE).boxed().forEach(num -> builder.append(BASE.charAt(random.nextInt(BASE.length() - 1))));
		return builder.toString();
	}
	
	public static String effectFormat(String str) {
		while(true) {
			int index = str.indexOf("@#");
			if (str.indexOf("@#") == -1) {
				break;
			}
			
			int nextIndex = str.indexOf("@", index + 3);
			String source = str.substring(index, nextIndex + 1);
			String target = str.substring(index + 2, nextIndex);
			str = str.replace(source, target);
		}
		
		return str;
	}
}
