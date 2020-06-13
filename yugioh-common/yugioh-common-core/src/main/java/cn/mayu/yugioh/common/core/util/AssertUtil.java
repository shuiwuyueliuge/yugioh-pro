package cn.mayu.yugioh.common.core.util;

import java.util.Collection;

public class AssertUtil {

	public static <T> boolean isIndexOutOfBounds(Collection<T> collection, int index) {
		return (index < 0 || collection.isEmpty() || collection.size() <= index) ? true : false;
	}
	
	public static boolean isNull(Object obj) {
		return obj == null;
	}

	public static boolean isEmpty(Collection collection) {
		return collection == null || collection.size() <= 0;
	}
}
