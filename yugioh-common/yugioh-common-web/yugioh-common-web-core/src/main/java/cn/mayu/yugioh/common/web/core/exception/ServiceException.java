package cn.mayu.yugioh.common.web.core.exception;

import cn.mayu.yugioh.common.web.core.intercept.web.ResultCodeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ServiceException extends RuntimeException {

    private int code;

    private String msg;

    private Exception e;

    public ServiceException(Exception e) {
        this(ResultCodeEnum.INTERNAL_SERVER_ERROR.getCode(), ResultCodeEnum.INTERNAL_SERVER_ERROR.getMsg(), e);
        this.setStackTrace(e.getStackTrace());
    }

    public ServiceException(String msg) {
        this(ResultCodeEnum.INTERNAL_SERVER_ERROR.getCode(), msg, null);
    }

    public ServiceException(int code, String message, Exception e) {
        super(message);
        this.code = code;
        this.e = e;
        this.msg = message;
    }
}