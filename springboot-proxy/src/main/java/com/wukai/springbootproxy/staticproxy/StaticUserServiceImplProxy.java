package com.wukai.springbootproxy.staticproxy;

import com.wukai.springbootproxy.UserServiceImpl;

/**
 * 静态代理类实现
 *
 * @author wukai
 * @since 2021-02-14 21:47:06
 */
public class StaticUserServiceImplProxy extends UserServiceImpl {
    UserServiceImpl userService;

    public StaticUserServiceImplProxy(UserServiceImpl userService) {
        super();
        this.userService = userService;
    }

    @Override
    public void addUser() {
        System.out.println("static proxy: before add user.");
        userService.addUser();
        System.out.println("static proxy: after add user.");
    }

    @Override
    public void deleteUser() {
        System.out.println("static proxy: before delete user.");
        userService.deleteUser();
        System.out.println("static proxy: after delete user.");
    }
}
