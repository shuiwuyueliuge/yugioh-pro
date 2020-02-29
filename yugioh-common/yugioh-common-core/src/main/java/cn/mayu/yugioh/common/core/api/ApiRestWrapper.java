package cn.mayu.yugioh.common.core.api;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME)
@Target({ TYPE, METHOD })
public @interface ApiRestWrapper {

	public static final String RESPONSE_BODY_WAPPER = ApiRestWrapper.class.getName();
}
