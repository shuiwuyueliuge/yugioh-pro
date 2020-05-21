package cn.mayu.yugioh.common.core.util;

import static cn.mayu.yugioh.common.core.util.AssertUtil.isNull;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class HttpServletRequestUtil {

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

	public static HttpServletRequest getRequest() {
		ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		return requestAttributes.getRequest();
	}
}
