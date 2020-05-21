package cn.mayu.yugioh.basic.gateway.route.convert;

public class BeanConvertFactory {
	
	public static <T, S> T convert(Converter<S, T> converter, S source) {
		return converter.apply(source);
	}
	
	public static <T, S> S reverse(Converter<S, T> converter, T target) {
		return converter.reverse(target);
	}
}