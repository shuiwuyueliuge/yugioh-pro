package cn.mayu.yugioh.sync.home.interceptor;

import static cn.mayu.yugioh.common.core.api.ResultCodeEnum.FAILURE;
import static cn.mayu.yugioh.common.core.api.ResultCodeEnum.SUCCESS;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import cn.mayu.yugioh.common.dto.sync.home.ResultProtoFactory;
import lombok.extern.slf4j.Slf4j;

@Component
@Aspect
@Slf4j
public class SyncDataInterceptor {

	@Pointcut("execution(* cn.mayu.yugioh.sync.home.service.SyncHomeServiceImpl.*(..))")
	public void cut() {}
	
	@Around(value = "cut()")
	public Object bizLog(ProceedingJoinPoint point) throws Throwable {
		ResultProtoFactory factory = new ResultProtoFactory();
		try {
			point.proceed();
			return factory.createResultModel(SUCCESS);
		} catch (Exception e) {
			log.error("persistent [{}] error [{}]", point.getArgs()[0], e);
			return factory.createResultModel(FAILURE);
		}
	}
}
