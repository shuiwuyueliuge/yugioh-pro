package cn.mayu.yugioh.common.core.bean;

public interface ModelFactory<S, T> {

	 T convert(S source);
}
