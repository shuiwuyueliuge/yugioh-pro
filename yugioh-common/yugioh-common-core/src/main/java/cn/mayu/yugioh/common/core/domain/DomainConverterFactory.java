package cn.mayu.yugioh.common.core.domain;

public interface DomainConverterFactory<S, T> {

	 T convert(S source);
}
