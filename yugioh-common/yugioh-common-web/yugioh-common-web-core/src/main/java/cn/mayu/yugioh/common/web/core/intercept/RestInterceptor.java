package cn.mayu.yugioh.common.web.core.intercept;

import org.slf4j.MDC;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

public class RestInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (handler instanceof HandlerMethod) {
            final HandlerMethod handlerMethod = (HandlerMethod) handler;
            final Class<?> clazz = handlerMethod.getBeanType();
            final Method method = handlerMethod.getMethod();
            if (clazz.isAnnotationPresent(RestController.class) || method.isAnnotationPresent(ResponseBody.class)) {
                if (request.getHeader("feign") == null) {
                    request.setAttribute("api", "api");
                }
            }
        }

        if (request.getHeader("traceId") != null) {
            MDC.put("traceId", request.getHeader("traceId"));
        }

        return true;
    }
}