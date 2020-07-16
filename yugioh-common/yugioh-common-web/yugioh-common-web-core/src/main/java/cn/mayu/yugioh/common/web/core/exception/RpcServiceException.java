package cn.mayu.yugioh.common.web.core.exception;

import cn.mayu.yugioh.common.web.core.intercept.web.ResultCodeEnum;
import feign.FeignException;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper=false)
public class RpcServiceException extends FeignException {

    public RpcServiceException(StackTraceElement[] stackTrace, String msg) {
        super(ResultCodeEnum.INTERNAL_SERVER_ERROR.getCode(), msg);
        this.setStackTrace(stackTrace);
    }
}
