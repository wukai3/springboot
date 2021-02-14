package com.wukai.springbootproxy;

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
