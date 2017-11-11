package com.epam.spring.core.course.aspects;

import com.epam.spring.core.course.bean.Event;
import com.epam.spring.core.course.loggers.EventLogger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ConsoleLoggerLimitAspect {
    private final int maxCount;
    private int currentCount = 0;
    private final EventLogger otherLogger;

    @Autowired
    public ConsoleLoggerLimitAspect(
            @Value("${console.logger.max:2}") int maxCount,
            @Qualifier("fileEventLogger") EventLogger otherLogger) {
        this.maxCount = maxCount;
        this.otherLogger = otherLogger;
    }


    @Around("execution(* *.logEvent(com.epam.spring.core.course.bean.Event)) " +
            "&& within(com.epam.spring.core.course.loggers.ConsoleEventLogger) " +
            "&& args(evt)")
    public void aroundLogEvent(ProceedingJoinPoint jp, Object evt) throws Throwable {
        if (currentCount < maxCount) {
            System.out.println("ConsoleEventLogger max count isn't reached. Continue...");
            currentCount++;
            jp.proceed(new Object[] {evt});
        } else {
            System.out.println("ConsoleEventLogger max count is reached. Logging to " +
            otherLogger.getName());
            otherLogger.logEvent((Event) evt);
        }
    }
}
