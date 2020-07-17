package cn.mayu.yugioh.common.web.core.intercept.web;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseBody<T> {

    private static final String SRT = "{\"code\":%s,\"msg\":\"%s\",\"traceId\":\"%s\",\"data\":\"%s\"}";

    private Integer code;

    private String msg;

    private String traceId;

    private T data;

    public ResponseBody(Integer resultCode, String errMsg, String traceId) {
        this(resultCode, errMsg, null, traceId);
    }

    public ResponseBody(T data, String traceId) {
        this(ResultCodeEnum.SUCCESS.getCode(), ResultCodeEnum.SUCCESS.toString(), data, traceId);
    }

    public ResponseBody(Integer resultCode, String errMsg, T data, String traceId) {
        this.setCode(resultCode);
        this.setMsg(errMsg);
        this.setData(data);
        this.setTraceId(traceId);
    }

    @Override
    public String toString() {
        return String.format(SRT, code, msg, traceId, data);
    }
}