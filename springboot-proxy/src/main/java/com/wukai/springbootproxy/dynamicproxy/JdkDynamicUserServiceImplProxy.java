package com.wukai.springbootproxy.dynamicproxy;

import java.lang.reflect.Proxy;

public class JdkDynamicUserServiceImplProxy {
    public static Object getProxy(Object object) {
        return Proxy.newProxyInstance(
                object.getClass().getClassLoader(),
                object.getClass().getInterfaces(),
                new InvokeHandlerImpl(object));
    }
}
