package cn.mayu.yugioh.common.core.util;

import static cn.mayu.yugioh.common.core.util.DateUtil.dayNow;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class FileUtil {

	public static void saveInFile(String path, String content) throws Exception {
		try (FileWriter fileWriter = new FileWriter(path, true);
			 BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
			bufferedWriter.write(content);
		}
	}
	
	public static String userDir() {
		return System.getProperty("user.dir");
	}
	
	public static String genTodayFileName() {
		return String.format("%s%sCardData-%s", userDir(), File.separator, dayNow());
	}
}
