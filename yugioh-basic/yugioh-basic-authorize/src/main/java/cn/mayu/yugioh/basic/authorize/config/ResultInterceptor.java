package cn.mayu.yugioh.basic.authorize.config;

import java.lang.reflect.Method;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import cn.mayu.yugioh.common.core.api.ApiRestWrapper;

public class ResultInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		if (handler instanceof HandlerMethod) {
			setAttribute(request, (HandlerMethod) handler);
		}
		
		return true;
	}
	
	private void setAttribute(HttpServletRequest request, final HandlerMethod handlerMethod) {
		final Class<?> clazz = handlerMethod.getBeanType();
		final Method method =  handlerMethod.getMethod();
		if (clazz.isAnnotationPresent(ApiRestWrapper.class) || method.isAnnotationPresent(ApiRestWrapper.class)) {
			request.setAttribute(ApiRestWrapper.RESPONSE_BODY_WAPPER, clazz.getAnnotation(ApiRestWrapper.class));
		}
	}
}
