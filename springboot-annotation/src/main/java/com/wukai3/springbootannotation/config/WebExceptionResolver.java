package com.wukai3.springbootannotation.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

/**
 * @author kaiwu3
 * @since 2020-12-08
 */
@Slf4j
@Component
public class WebExceptionResolver implements HandlerExceptionResolver {

    @Override
    public ModelAndView resolveException(HttpServletRequest request,
                                         HttpServletResponse response,
                                         Object handler,
                                         Exception ex) {
        log.error("Request:\"{}\" faced error: ", request.getServletPath(), ex);
        HttpStatus status = INTERNAL_SERVER_ERROR;
        String message = null;
        HttpHeaders headers = null;
        if (ex instanceof CommonException) {
            status = ((CommonException) ex).getStatus();
            message = ex.getMessage();
        }
        response.setStatus(status.value());
        if (!StringUtils.hasLength(message)) {
            message = status.getReasonPhrase();
        }
        try {
            String responseBody = String.format("{\"message\": \"%s\"}", message.replace("\"", "\\\""));
            response.getWriter().write(responseBody);
        } catch (IOException e) {
            log.warn("IOException while writing response body:", ex);
        }
        return new ModelAndView();
    }
}
