package cn.mayu.yugioh.common.web.core.intercept.trace;

import com.google.common.collect.Maps;
import lombok.Data;
import org.slf4j.MDC;
import java.util.Map;
import static java.util.UUID.randomUUID;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Stream.of;

@Data
public abstract class Tracker {

    private String traceId;

    private static final String REGEX = "-";

    private Map<String, String> map;

    public void track() {
        Map<String, String> map = Maps.newHashMap();
        String traceId = MDC.get("traceId");
        if (traceId == null) {
            traceId = of(randomUUID().toString().split(REGEX)).collect(joining());
        }

        map.put("traceId", traceId);
        genTraceParam(map);
        addTraceParam(map);
        this.traceId = map.get("traceId");
        this.map = map;
    }

    protected abstract void genTraceParam(Map<String, String> map);

    private void addTraceParam(Map<String, String> map) {
        map.entrySet().forEach(entry -> MDC.put(entry.getKey(), entry.getValue()));
    }

    public String getTraceId() {
        return traceId;
    }

    public void removeTraceParam() {
        MDC.clear();
    }
}
