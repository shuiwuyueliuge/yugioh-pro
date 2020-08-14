package cn.mayu.yugioh.common.web.core.intercept;

import cn.mayu.yugioh.common.web.core.exception.RpcServiceException;
import cn.mayu.yugioh.common.web.core.exception.ServiceException;
import cn.mayu.yugioh.common.web.core.intercept.trace.Tracker;
import cn.mayu.yugioh.common.web.core.intercept.trace.WebTracker;
import cn.mayu.yugioh.common.web.core.intercept.web.Pageable;
import cn.mayu.yugioh.common.web.core.intercept.web.ResponseBody;
import cn.mayu.yugioh.common.web.core.intercept.web.ResultCodeEnum;
import cn.mayu.yugioh.common.web.core.intercept.web.RpcBody;
import cn.mayu.yugioh.common.web.core.model.vo.PageVO;
import cn.mayu.yugioh.common.web.core.util.HttpRequestHelper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@Aspect
@ControllerAdvice
@Slf4j
public class RestWrapperInterceptor implements ResponseBodyAdvice<Object> {

    private static final String LOG_ERROR_TEMPLATE = "Error:[{}]";

    private static final String WRAPPER_TEMPLATE = "RestResponse:[{}]";

    private Tracker tracker;

    @Pointcut("@within(org.springframework.web.bind.annotation.RestController) || @within(org.springframework.stereotype.Controller)")
    public void webCut() {}

    @Around(value = "webCut()")
    public Object webLog(ProceedingJoinPoint point) throws Throwable {
        this.tracker = new WebTracker();
        return LogMsgBuilder.initBuilder(point).infoLog(tracker);
    }

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        if (tracker == null) {
            this.tracker = new WebTracker();
            this.tracker.track();
        }

        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request,
                                  ServerHttpResponse response) {
        try {
            Object res = null;
            Object attribute = HttpRequestHelper.getAttribute("api");
            if (attribute != null) {
                res = wrapApiResponse(body);
            }

            Object head = HttpRequestHelper.getHeader("feign");
            if (head != null) {
                res = wrapRpcResponse(body);
            }

            log.info(WRAPPER_TEMPLATE, res);
            if (returnType.getMethod().getReturnType().getTypeName().equals("java.lang.String")) {
                res = res.toString();
            }

            return res;
        } finally {
            if (tracker != null) {
                tracker.removeTraceParam();
            }
        }
    }

    private Object wrapApiResponse(Object body) {
        String traceId = tracker.getTraceId();
        if (body instanceof ServiceException) {
            ServiceException e = (ServiceException) body;
            if (e.getE() == null) {
                log.info(LOG_ERROR_TEMPLATE, e.getMsg());
            } else {
                log.error(LOG_ERROR_TEMPLATE, e.getE());
            }

            return new ResponseBody<>(e.getCode(), e.getMsg(), traceId);
        }

        if (body instanceof RpcServiceException) {
            RpcServiceException e = (RpcServiceException) body;
            log.error(LOG_ERROR_TEMPLATE, e);
            return new ResponseBody<>(ResultCodeEnum.INTERNAL_SERVER_ERROR.getCode(), ResultCodeEnum.INTERNAL_SERVER_ERROR.getMsg(), traceId);
        }

        if (body instanceof PageVO) {
            PageVO<Object> pageVO = (PageVO) body;
            return new Pageable<>(pageVO.getCount(), pageVO.getCurrentPage(), pageVO.getData(), traceId);
        }

        return new ResponseBody<>(body, traceId);
    }

    private Object wrapRpcResponse(Object body) {
        if (body instanceof ServiceException) {
            ServiceException exception = (ServiceException) body;
            log.error("{}", exception.getE());
            RpcBody responseBody = new RpcBody();
            responseBody.setStackTrace(exception.getE().getStackTrace());
            responseBody.setMsg(HttpRequestHelper.getLocalAddr() + " - " + exception.getE().getMessage());
            responseBody.setCode(ResultCodeEnum.INTERNAL_SERVER_ERROR.getCode());
            return responseBody;
        }

        if (body instanceof RpcServiceException) {
            RpcServiceException e = (RpcServiceException) body;
            log.error("{}", e);
            RpcBody responseBody = new RpcBody();
            responseBody.setStackTrace(e.getStackTrace());
            responseBody.setMsg(e.getMessage());
            responseBody.setCode(ResultCodeEnum.INTERNAL_SERVER_ERROR.getCode());
            return responseBody;
        }

        RpcBody responseBody = new RpcBody();
        responseBody.setCode(ResultCodeEnum.SUCCESS.getCode());
        responseBody.setData(body);
        return responseBody;
    }
}