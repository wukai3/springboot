package com.wukai3.springbootannotation.annotation;


import com.wukai3.springbootannotation.config.CommonException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * @author kaiwu3
 * @since 2020-12-08
 */
@Slf4j
@Component
public class AnnotationImpl implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // if not mapped to function, return true
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        // method annotation, 反射
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();

        // 检查方法级别的注解@Report是否存在
        if (method.isAnnotationPresent(Report.class)) {
            Report report = method.getAnnotation(Report.class);
            log.info("===============@Report Annotation==================");
            log.info("type: {}, level: {}", report.type(), report.level());
            log.info("===================================================");
        }

        // 获取所有参数级别的Annotation:
        Annotation[][] annos = method.getParameterAnnotations();
        // 第一个参数（索引为0）的所有Annotation:
        Annotation[] annosOfFirst = annos[0];
        for (Annotation annotation: annosOfFirst) {
            String first = request.getParameter("name");
            // @NotNull注解
            if (annotation instanceof NotNull) {
                log.info("==============@NotNull Annotation==================");
                if (!StringUtils.hasLength(first)) {
                    throw new CommonException(HttpStatus.BAD_REQUEST, "the query parameter is empty." );
                }
            }
            // @Range注解
            if (annotation instanceof Range) {
                log.info("===============@Range Annotation===================");
                Range range = (Range) annotation;
                if (first.length() < range.min()) {
                    throw new CommonException(HttpStatus.BAD_REQUEST, "the query parameter's length too short. min-length: " + range.min());
                }
                if (first.length() > range.max()) {
                    throw new CommonException(HttpStatus.BAD_REQUEST, "the query parameter's length too long. max-length: " + range.max());
                }
            }
        }
        return true;
    }

    /**
     * This implementation is empty.
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    /**
     * This implementation is empty.
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }
}
