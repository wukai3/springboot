package com.wukai.springbootproxy.dynamicproxy;

import org.springframework.cglib.proxy.Enhancer;

/**
 * 利用CGLIB生成动态代理类
 * <p>注意：CGLIB方式通过派生自目标类的方式实现动态代理，然后覆写目标类所有的方法。
 * 该方法无需目标类有接口，但是对于final修饰的方法，无法通过CGLIB方式实现动态代理</p>
 *
 * @author wukai
 * @since 2021-02-14 21:47:06
 */
public class CglibDynamicUserServiceImplProxy {
    // 通过反射，动态生成代理类对象
    public static Object getProxy(Class<?> classInstance) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(classInstance);
        enhancer.setCallback(new MethodInterceptorImpl());

        return enhancer.create();
    }
}
