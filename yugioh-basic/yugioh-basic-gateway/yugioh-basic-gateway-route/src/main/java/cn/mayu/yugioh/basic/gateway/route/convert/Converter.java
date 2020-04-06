package cn.mayu.yugioh.basic.gateway.route.convert;

import java.util.List;

public interface Converter<S, T> {

	S reverse(T target);

	T apply(S source);

	default List<S> reverseList(List<T> target) {
		throw new RuntimeException("you shoud override reverseList()");
	}
	
	default List<T> applyList(List<S> source) {
		throw new RuntimeException("you shoud override applyList()");
	}
}
