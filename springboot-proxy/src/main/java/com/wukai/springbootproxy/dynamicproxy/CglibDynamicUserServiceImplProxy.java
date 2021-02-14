package com.wukai.springbootproxy.dynamicproxy;

import org.springframework.cglib.proxy.Enhancer;

public class CglibDynamicUserServiceImplProxy {
    public static Object getProxy(Class<?> type) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(type);
        enhancer.setCallback(new MethodInterceptorImpl());

        return enhancer.create();
    }
}
