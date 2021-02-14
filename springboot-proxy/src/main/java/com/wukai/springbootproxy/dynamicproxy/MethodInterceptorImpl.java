package com.wukai.springbootproxy.dynamicproxy;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * CGLIB动态代理的增强实现类
 *
 * @author wukai
 * @since 2021-02-14 21:47:06
 */
public class MethodInterceptorImpl implements MethodInterceptor {
    @Override
    public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        System.out.println("CGLIB dynamic proxy: before ...");
        Object result = methodProxy.invokeSuper(proxy, args);
        System.out.println("CGLIB dynamic proxy: after ...");
        return result;
    }
}
