package com.wukai.springbootproxy;

/**
 * @author wukai
 * @since 2021-02-14 21:47:06
 */
public class UserServiceImpl implements UserService {
    @Override
    public void addUser() {
        System.out.println("add user");
    }

    @Override
    public void deleteUser() {
        System.out.println("delete user");
    }
}
