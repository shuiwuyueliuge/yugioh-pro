package cn.mayu.yugioh.basic.gateway.route.entity;

import java.util.function.Function;

public interface Converter<S, T> extends Function<S, T> {

}
