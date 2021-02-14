package com.wukai.springbootproxy.dynamicproxy;

import java.lang.reflect.Proxy;

/**
 * 利用JDK方式生成动态代理类
 * <p>注意：JDK是由JAVA原生支持的，实现简单，是通过实现目标类接口的方式实现动态代理，需要目标类有接口</p>
 *
 * @author wukai
 * @since 2021-02-14 21:47:06
 */
public class JdkDynamicUserServiceImplProxy {
    public static Object getProxy(Object object) {
        // 通过反射信息，动态生成代理类对象
        return Proxy.newProxyInstance(
                object.getClass().getClassLoader(),
                object.getClass().getInterfaces(),
                new InvokeHandlerImpl(object));
    }
}
