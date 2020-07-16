package cn.mayu.yugioh.common.web.core.intercept;

import cn.mayu.yugioh.common.web.core.intercept.trace.BasicTracker;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
@Slf4j
public class TraceInterceptor {

    @Pointcut("@within(org.springframework.stereotype.Service)")
    public void serviceCut() {
    }

    @Pointcut("@within(org.springframework.stereotype.Repository)")
        public void dbCut() {
    }

    @Pointcut("@within(org.springframework.cloud.openfeign.FeignClient)")
    public void clientCut() {
    }

    @Around(value = "serviceCut()")
    public Object doServiceLog(ProceedingJoinPoint point) throws Throwable {
        return write(point);
    }

    @Around(value = "clientCut()")
    public Object doClientLog(ProceedingJoinPoint point) throws Throwable {
        return write(point);
    }

    @Around(value = "dbCut()")
    public Object doDbLog(ProceedingJoinPoint point) throws Throwable {
        return write(point);
    }

    private Object write(ProceedingJoinPoint point) throws Throwable {
        return LogMsgBuilder.initBuilder(point).infoLog(BasicTracker.getInstance());
    }
}