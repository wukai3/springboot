package com.wukai3.springbootannotation.config;

import com.wukai3.springbootannotation.annotation.AnnotationImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DelegatingWebMvcConfiguration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

/**
 * @author kaiwu3
 * @since 2020-12-08
 */
@Configuration
public class WebConfigurer extends DelegatingWebMvcConfiguration {
    @Autowired
    private AnnotationImpl annotation;

    @Override
    public void addInterceptors (InterceptorRegistry interceptorRegistry) {
        interceptorRegistry.addInterceptor(annotation).addPathPatterns("/v1/**");
    }
}
