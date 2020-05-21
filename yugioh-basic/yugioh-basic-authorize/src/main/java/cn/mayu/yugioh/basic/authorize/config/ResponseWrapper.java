package cn.mayu.yugioh.basic.authorize.config;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import cn.mayu.yugioh.common.core.api.ApiRestWrapper;
import cn.mayu.yugioh.common.core.api.RestResultModelFactory;
import cn.mayu.yugioh.common.core.api.ResultCodeEnum;
import cn.mayu.yugioh.common.core.util.HttpServletRequestUtil;

@ControllerAdvice
public class ResponseWrapper implements ResponseBodyAdvice<Object> {

	@Override
	public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
		HttpServletRequest request = HttpServletRequestUtil.getRequest();
		Object ann = request.getAttribute(ApiRestWrapper.RESPONSE_BODY_WAPPER);
		return ann == null ? false : true;
	}

	@Override
	public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
			Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request,
			ServerHttpResponse response) {
		RestResultModelFactory<Object> factory = new RestResultModelFactory<Object>();
		return factory.createResultModel(ResultCodeEnum.SUCCESS, body);
	}
}
