package cn.mayu.yugioh.common.core.domain;

import java.util.function.Function;

public interface DomainConverterFactory<S, T> extends Function<S, T>{

	 T convert(S source);
}
