package cn.mayu.yugioh.common.core.domain;

import java.util.List;

public interface DomainConverterFactory<S, T> {

	default T convert(S source) {
		return null;
	}

	default S reversal(T target) {
		return null;
	}
	
	default List<T> convertList(S source) {
		return null;
	}

	default List<S> reversalList(T target) {
		return null;
	}
	
	default List<T> convertList(List<S> source) {
		return null;
	}

	default List<S> reversalList(List<T> target) {
		return null;
	}
	
	default T convert(List<S> source) {
		return null;
	}

	default S reversal(List<T> target) {
		return null;
	}
}
