package com.wukai.springbootproxy.dynamicproxy;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class MethodInterceptorImpl implements MethodInterceptor {
    @Override
    public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        System.out.println("CGLIB dynamic proxy: before ...");
        Object result = methodProxy.invokeSuper(proxy, args);
        System.out.println("CGLIB dynamic proxy: after ...");
        return result;
    }
}
