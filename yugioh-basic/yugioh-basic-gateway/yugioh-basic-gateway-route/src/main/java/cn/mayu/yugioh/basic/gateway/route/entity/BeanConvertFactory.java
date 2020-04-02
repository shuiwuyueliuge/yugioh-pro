package cn.mayu.yugioh.basic.gateway.route.entity;

public class BeanConvertFactory {
	
	public static <T, S> T convert(Converter<S, T> converter, S source) {
		return converter.apply(source);
	}
}