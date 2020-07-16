package cn.mayu.yugioh.common.web.core.intercept;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;
import cn.mayu.yugioh.common.web.core.annotation.ArgumentMark;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.core.MethodParameter;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.support.GenericConversionService;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Slf4j
public class UnderlineToCamelArgumentResolver implements HandlerMethodArgumentResolver {

    private ConversionService conversionService;

    private static Pattern pattern = Pattern.compile("_(\\w)");

    public UnderlineToCamelArgumentResolver(Converter<?, ?>... converter) {
        GenericConversionService genericConversionService = new GenericConversionService();
        Stream.of(converter).forEach(genericConversionService::addConverter);
        this.conversionService = genericConversionService;
    }

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.hasParameterAnnotation(ArgumentMark.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer container,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
        return handleParameterNames(parameter, webRequest);
    }

    private Object handleParameterNames(MethodParameter parameter, NativeWebRequest webRequest) {
        try {
            Object obj = parameter.getParameterType().newInstance();
            BeanWrapper wrapper = PropertyAccessorFactory.forBeanPropertyAccess(obj);
            wrapper.setConversionService(conversionService);
            webRequest.getParameterNames().forEachRemaining(paramName -> {
                Object o = webRequest.getParameter(paramName);
                wrapper.setPropertyValue(underLineToCamel(paramName), o);
            });
            return obj;
        } catch (Exception e) {
            log.error("handleParameterNames error:[{}]", e);
            return null;
        }
    }

    private static String underLineToCamel(String source) {
        Matcher matcher = pattern.matcher(source);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
        }

        matcher.appendTail(sb);
        return sb.toString();
    }
}