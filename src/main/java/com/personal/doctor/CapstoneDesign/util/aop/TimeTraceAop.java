package com.personal.doctor.CapstoneDesign.util.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class TimeTraceAop {

    @Around("execution(* com.personal.doctor.CapstoneDesign.hospital.controller..*(..))" +
            " || execution(* com.personal.doctor.CapstoneDesign.chat.controller..*(..))" +
            " || execution(* com.personal.doctor.CapstoneDesign.community.controller..*(..))" +
            " || execution(* com.personal.doctor.CapstoneDesign.user.controller..*(..))" +
            " || execution(* com.personal.doctor.CapstoneDesign.userDetail.controller..*(..))")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();

        try {
            return joinPoint.proceed();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;

            System.out.println(joinPoint.toString() + " " + timeMs + "ms");
        }
    }

}
