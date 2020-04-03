package cn.mayu.yugioh.basic.gateway.route.convert;

public interface Converter<S, T> {

	S reverse(T target);
	
	T apply(S source);
}
