package com.example.share.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * 使用aop监控Mapper执行时间
 * Created by me on 2017/2/20.
 */
@Aspect
@Component
public class MapperAspect {
    private static final Logger logger = LoggerFactory.getLogger(MapperAspect.class);

    @AfterReturning("execution(* com.example.share.mapper..*Mapper.*(..))")
    public void logServiceAccess(JoinPoint joinPoint) {
        logger.info("Completed: " + joinPoint);
    }


    /**
     * 监控com.caiyi.financial.nirvana..*Mapper包及其子包的所有public方法
     */
    @Pointcut("execution(* com.example.share.mapper.*Mapper.*(..))")
    private void pointCutMethod() {
    }

    /**
     * 声明环绕通知
     * @param pjp
     * @return
     * @throws Throwable
     */
    @Around("pointCutMethod()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        long begin = System.nanoTime();
        Object obj = pjp.proceed();
        long end = System.nanoTime();

        logger.info("调用Mapper方法：{}，\n参数：{}，\n执行耗时：{}纳秒，\r\n耗时：{}毫秒",
                pjp.getSignature().toString(), Arrays.toString(pjp.getArgs()),
                (end - begin), (end - begin) / 1000000);
        return obj;
    }
}
