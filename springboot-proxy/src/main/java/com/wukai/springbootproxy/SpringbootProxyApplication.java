package com.wukai.springbootproxy;

import com.wukai.springbootproxy.dynamicproxy.CglibDynamicUserServiceImplProxy;
import com.wukai.springbootproxy.dynamicproxy.JdkDynamicUserServiceImplProxy;
import com.wukai.springbootproxy.staticproxy.StaticUserServiceImplProxy;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbootProxyApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootProxyApplication.class, args);
        execute();
    }

    public static void execute() {
        UserServiceImpl userService = new UserServiceImpl();
        userService.addUser();
        userService.deleteUser();
        System.out.println("===================");

        StaticUserServiceImplProxy staticUserServiceImplProxy = new StaticUserServiceImplProxy(userService);
        staticUserServiceImplProxy.addUser();
        staticUserServiceImplProxy.deleteUser();
        System.out.println("===================");

        UserServiceImpl cglibProxy = (UserServiceImpl) CglibDynamicUserServiceImplProxy.getProxy(UserServiceImpl.class);
        cglibProxy.addUser();
        cglibProxy.deleteUser();
        System.out.println("===================");

        UserService jkdProxy = (UserService) JdkDynamicUserServiceImplProxy.getProxy(userService);
        jkdProxy.addUser();
        jkdProxy.deleteUser();
    }
}
