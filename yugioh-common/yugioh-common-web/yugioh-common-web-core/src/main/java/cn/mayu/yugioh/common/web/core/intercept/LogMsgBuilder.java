package cn.mayu.yugioh.common.web.core.intercept;

import cn.mayu.yugioh.common.web.core.intercept.trace.Tracker;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import java.util.Arrays;

@Slf4j
public class LogMsgBuilder {

    private  String className;

    private  String method;

    private String args;

    private ProceedingJoinPoint point;

    private static final String LOG_START_TEMPLATE = "[{}.{}] Args:{}";

    private static final String LOG_END_TEMPLATE = "[{}.{}] Result:[{}] ProceedTime:[{}ms]";

    private LogMsgBuilder() {}

    public static LogMsgBuilder initBuilder(ProceedingJoinPoint point) {
        LogMsgBuilder logMsgBuilder = new LogMsgBuilder();
        logMsgBuilder.className = point.getSignature().getDeclaringTypeName();
        logMsgBuilder.method = ((MethodSignature) point.getSignature()).getMethod().getName();
        logMsgBuilder.args = Arrays.toString(point.getArgs());
        logMsgBuilder.point = point;
        return logMsgBuilder;
    }

    public Object infoLog(Tracker tracker) throws Throwable {
        Object result;
        tracker.track();
        long time = System.currentTimeMillis();
        log.info(LOG_START_TEMPLATE, className, method, args);
        result = point.proceed();
        long proceedTime = System.currentTimeMillis() - time;
        log.info(LOG_END_TEMPLATE, className, method, result, proceedTime);
        return result;
    }
}