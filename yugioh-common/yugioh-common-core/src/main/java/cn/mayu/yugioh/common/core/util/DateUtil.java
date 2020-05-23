package cn.mayu.yugioh.common.core.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class DateUtil {

	public static String dayNow() {
		return LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
	}
	
	public static long toEpochMilli(LocalDateTime localDateTime) {
		return localDateTime.toInstant(ZoneOffset.of("+8")).toEpochMilli();
	}

	public static LocalDate toLocalDate(String time) {
		return LocalDate.parse(time, DateTimeFormatter.ofPattern("yyyyMMdd"));
	}
}
