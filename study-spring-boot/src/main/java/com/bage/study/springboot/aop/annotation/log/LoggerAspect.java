package com.bage.study.springboot.aop.annotation.log;

import com.bage.study.springboot.aop.order.HelloAopOrderService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggerAspect {

    private static final Logger log = LoggerFactory.getLogger(LoggerAspect.class);


    @Around(value = "@annotation(com.bage.study.springboot.aop.annotation.log.Logger)")
    public Object withMethod(ProceedingJoinPoint pjp) throws Throwable {
        // start stopwatch
        Object result = null;
        String className = pjp.getTarget().getClass().getSimpleName();
        String methodName = pjp.getSignature().getName();
        log.info("LoggerAspect className = {}, method = {}, param = {}", className, methodName, pjp.getArgs());
        result = pjp.proceed();
        log.info("LoggerAspect className = {}, method = {}, result = {}", className, methodName, result);
        // stop stopwatch

        Class returnType = ((MethodSignature) pjp.getSignature()).getReturnType();

        Object o = returnType.newInstance();
        if(o instanceof HelloAopOrderService.Hhhh){
            log.info("isAssignableFrom {}", returnType.getName());
        }
        return result;
    }

}
