package cn.mayu.yugioh.common.core.domain;

import java.util.List;

public interface DomainConverterFactory<S, T> {

	T convert(S source);

	default S reversal(T target) {
		return null;
	}
	
	default List<T> convertList(List<S> source) {
		return null;
	}

	default List<S> reversalList(List<T> target) {
		return null;
	}
}
