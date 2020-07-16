package cn.mayu.yugioh.common.web.core.intercept;

import cn.mayu.yugioh.common.web.core.exception.RpcServiceException;
import cn.mayu.yugioh.common.web.core.exception.ServiceException;
import cn.mayu.yugioh.common.web.core.intercept.web.ResultCodeEnum;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import javax.validation.ConstraintViolationException;

@RestControllerAdvice
public class WebExceptionWrapper {

    @ExceptionHandler(value = BindException.class)
    public ServiceException handleBindException(BindException ex){
        return new ServiceException(ResultCodeEnum.PARAM_BIND_ERROR.getCode(), ResultCodeEnum.PARAM_BIND_ERROR.toString(), ex);
    }

    @ExceptionHandler(value = ConstraintViolationException.class)
    public ServiceException handleBindGetException(ConstraintViolationException ex){
        return new ServiceException(ResultCodeEnum.PARAM_VALID_ERROR.getCode(), ResultCodeEnum.PARAM_VALID_ERROR.toString(), ex);
    }

    @ExceptionHandler(RpcServiceException.class)
    public RpcServiceException exception(RpcServiceException e) {
        return e;
    }

    @ExceptionHandler(Exception.class)
    public ServiceException exception(Exception e) {
        return new ServiceException(e);
    }
}