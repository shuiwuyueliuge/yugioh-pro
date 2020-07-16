package cn.mayu.yugioh.common.web.core.intercept;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.slf4j.MDC;

public class FeignBasicAuthRequestInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate template) {
        template.header("traceId", MDC.get("traceId"));
        template.header("feign", "true");
    }
}
