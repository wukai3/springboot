package com.wukai.ioc.springbootbeanpostprocessor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.ui.context.support.UiApplicationContextUtils;

@SpringBootApplication
public class SpringbootBeanpostprocessorApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(SpringbootBeanpostprocessorApplication.class, args);
        People people = (People) context.getBean("people");
        System.out.println(people);
    }

}
