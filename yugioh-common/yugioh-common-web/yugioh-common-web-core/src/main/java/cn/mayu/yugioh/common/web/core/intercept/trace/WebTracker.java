package cn.mayu.yugioh.common.web.core.intercept.trace;

import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.Map;
import static cn.mayu.yugioh.common.web.core.util.HttpRequestHelper.*;

@Data
@EqualsAndHashCode(callSuper=false)
public class WebTracker extends Tracker {

    private String requestMethod;

    private String uri;

    @Override
    protected void genTraceParam(Map<String, String> map) {
        this.requestMethod = getMethod();
        this.uri = getRequestURI();
        map.put("requestMethod", requestMethod);
        map.put("uri", uri);
        String traceId = map.get("traceId");
        String local = getLocalAddr();
        String ipPostfix = local.substring(local.lastIndexOf(".") + 1);
        if (getHeader("feign") != null) {
            map.put("traceId", traceId);
        } else {
            map.put("traceId", String.format("%s|%s", traceId, ipPostfix));
        }
    }
}
