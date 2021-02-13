package com.wukai.ioc.springbootbeanpostprocessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class CustomizeBeanPostProcessorHandler implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if(bean instanceof People) {
            People people = (People) bean;
            if (people.getName() == null) {
                people.setName("wukai");
            }
            return people;
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if(bean instanceof People) {
            People people = (People) bean;
            if (people.getPhone() == null) {
                people.setPhone("138xxx3018");
            }
            return people;
        }
        return bean;
    }
}
