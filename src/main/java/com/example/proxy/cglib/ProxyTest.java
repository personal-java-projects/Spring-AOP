package com.example.proxy.cglib;

import com.example.proxy.jdk.TargetInterface;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyTest {

    public static void main(final String[] args) {

        // 创建目标对象
        final Target target = new Target();

        // 获得增强对象
        final Advice advice = new Advice();

        // 返回值 就是动态生成的代理对象。基于cgli
        // 1、创建增强器
        Enhancer enhancer = new Enhancer();

        // 2、设置父类（就是目标类Target）
        enhancer.setSuperclass(Target.class);

        // 3、设置回调, 需要一个回调类，一般给一个Callback下面的子接口，叫MethodInterceptor
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            // 相当于invoke方法
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                // 执行前置
                advice.before();

                // 执行目标
                Object invoke = method.invoke(target, args);

                // 执行后置
                advice.after();

                return invoke;
            }
        });

        // 4、创建代理对象
        Target proxy = (Target) enhancer.create();

        // 调用代理对象的方法
        proxy.save();
    }
}
