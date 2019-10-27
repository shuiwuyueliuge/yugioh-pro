package cn.mayu.yugioh.common.core.factory;

public interface ModelFactory<S, T> {

	 T convert(S source);
}
