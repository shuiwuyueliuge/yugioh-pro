package cn.mayu.yugioh.common.core.util;

import java.security.MessageDigest;

public class Md5Util {

	private static final String CHARS = "0123456789ABCDEF";

	public static String md5(String input) throws Exception {
		MessageDigest md5 = MessageDigest.getInstance("MD5");
		md5.update(input.getBytes());
		byte[] r = md5.digest();
		StringBuilder builder = new StringBuilder();
		for (byte c : r) {
			builder.append(CHARS.toCharArray()[(c >>> 4) & 15]);
			builder.append(CHARS.toCharArray()[c & 15]);
		}

		return builder.toString();
	}
}
