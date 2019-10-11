package cn.mayu.yugioh.common.core.util;

public class StringUtil {
	
	private static final String SPECIAL = "[`~!@#$%^&*()+=|{}／';'\\[\\][\n][ ].<>/?~！@#￥%……&*（）——+|{}【】‘；”“’ 、]";
	
	private static final String LETTER = "[a-zA-Z]";
	
	private static final String REGEX = "(「-)";
	
	public static String replaceSpecialChar(String character, String replacement) {
		return character.replaceAll(SPECIAL, replacement);
	}
	
	public static String replaceLetter(String character, String replacement) {
		return character.replaceAll(LETTER, replacement);
	}
	
	public static String cardDescLetterFormat(String character) {
		return character.replaceAll(SPECIAL, "").replaceAll(LETTER, "").replaceAll(REGEX, "「");
	}
	
	public static String cardDescFormat(String character) {
		return character.replaceAll(SPECIAL, "").replaceAll(REGEX, "「");
	}
}
