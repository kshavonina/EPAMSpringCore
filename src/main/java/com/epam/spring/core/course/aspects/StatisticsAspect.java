package com.epam.spring.core.course.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Aspect
public class StatisticsAspect {
    private Map<Class<?>, Integer> counter = new HashMap<>();

    public Map<Class<?>, Integer> getCounter() {
        return counter;
    }

    @Pointcut("execution(* *.logEvent(..))")
    private void allLogEventMethods() {}

    @AfterReturning("allLogEventMethods()")
    public void count(JoinPoint jp) {
        Class<?> clazz = jp.getTarget().getClass();

        if (!counter.containsKey(clazz)) {
            counter.put(clazz, 0);
        }

        counter.put(clazz, counter.get(clazz) + 1);
    }
}
