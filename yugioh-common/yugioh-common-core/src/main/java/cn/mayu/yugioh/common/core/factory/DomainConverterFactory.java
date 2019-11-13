package cn.mayu.yugioh.common.core.factory;

public interface DomainConverterFactory<S, T> {

	 T convert(S source);
}
