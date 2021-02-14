package com.wukai.ioc.springbootbeanpostprocessor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringbootBeanpostprocessorApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(SpringbootBeanpostprocessorApplication.class, args);
        People people = (People) context.getBean("people");
        System.out.println(people);
        SpringApplication.exit(context);
    }

}
