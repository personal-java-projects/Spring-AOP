package com.example.anno;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component("myAspect")
@Aspect // 标注当前MyAspect是一个切面类
public class MyAspect {

//    @Before("execution(* com.example.anno.*.*(..))")
    public void before() {
        System.out.println("前置增强。。。");
    }

    public void afterReturning () {
        System.out.println("后置增强");
    }

    // ProceedingJoinPoint: 正在执行的连接点,就是切点
//    @Around("execution(* com.example.anno.*.*(..))")
    @Around("myPoint()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("环绕前增强。。。");
        // 切点方法
        Object proceed = proceedingJoinPoint.proceed();
        System.out.println("环绕后增强。。。");

        return proceed;
    }

//    @AfterThrowing("execution(* com.example.anno.*.*(..))")
    public void afterThrowing() {
        System.out.println("异常抛出增强。。。");
    }

//    @After("execution(* com.example.anno.*.*(..))")
    @After("MyAspect.myPoint()")
    public void after() {
        System.out.println("最终增强。。。");
    }

    // 定义切点表达式
    @Pointcut("execution(* com.example.anno.*.*(..))")
    public void myPoint(){}
}
