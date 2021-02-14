package com.wukai.springbootproxy.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class InvokeHandlerImpl implements InvocationHandler {
    private Object realObj;

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
