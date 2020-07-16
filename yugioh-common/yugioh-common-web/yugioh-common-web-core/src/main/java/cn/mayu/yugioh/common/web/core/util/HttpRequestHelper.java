package cn.mayu.yugioh.common.web.core.util;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpServletRequest;

public class HttpRequestHelper {

    private static HttpServletRequest request() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        return attributes.getRequest();
    }

    public static String getHeader(String headerName) {
        if (headerName == null) throw new IllegalArgumentException();
        return request().getHeader(headerName);
    }

    public static String getRequestURI() {
        return request().getRequestURI();
    }

    public static String getMethod() {
        return request().getMethod();
    }

    public static Object getAttribute(String name) {
        if (name == null) throw new IllegalArgumentException();
        return request().getAttribute(name);
    }

    public static String getParam(String paramName) {
        if (paramName == null) throw new IllegalArgumentException();
        HttpServletRequest request = request();
        return request.getParameter(paramName);
    }

    public static String getLocalAddr() {
        return request().getLocalAddr();
    }
}