package com.wukai.springbootproxy.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * JDK动态代理类的增强实现类
 *
 * @author wukai
 * @since 2021-02-14 21:47:06
 */
public class InvokeHandlerImpl implements InvocationHandler {
    private final Object realObj;

    public InvokeHandlerImpl(Object object) {
        super();
        this.realObj = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("Jdk dynamic proxy: before ...");
        Object result = method.invoke(realObj, args);
        System.out.println("Jdk dynamic proxy: after ...");
        return result;
    }
}
