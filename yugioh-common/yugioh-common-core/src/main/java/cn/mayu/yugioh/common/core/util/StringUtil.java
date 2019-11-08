package cn.mayu.yugioh.common.core.util;

public class StringUtil {
	
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
