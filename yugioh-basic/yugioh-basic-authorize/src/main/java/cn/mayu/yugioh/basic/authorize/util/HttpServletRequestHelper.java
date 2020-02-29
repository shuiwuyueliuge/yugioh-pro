package cn.mayu.yugioh.basic.authorize.util;

import javax.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import static cn.mayu.yugioh.common.core.util.AssertUtil.*;

public class HttpServletRequestHelper {

	public static String getParam(String paramName) {
		if (isNull(paramName)) throw new IllegalArgumentException();
		HttpServletRequest request = getRequest();
		return request.getParameter(paramName);
	}
	
	public static String getHeader(String paramName) {
		if (isNull(paramName)) throw new IllegalArgumentException();
		HttpServletRequest request = getRequest();
		return request.getHeader(paramName);
	}

	private static HttpServletRequest getRequest() {
		ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		return requestAttributes.getRequest();
	}
}
