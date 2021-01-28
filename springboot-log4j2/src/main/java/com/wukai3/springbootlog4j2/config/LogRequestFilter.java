package com.wukai3.springbootlog4j2.config;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.MDC;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.WebUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Component
public class LogRequestFilter extends OncePerRequestFilter implements Ordered {
    /**
     * put filter at the end of all other filters to make sure we are processing after all others
     */
    private int order = Ordered.LOWEST_PRECEDENCE - 8;
    private final Log logger = LogFactory.getLog(getClass());
    private final static String TRACKING_KEY = "trackingId";

    @Override
    public int getOrder() {
        return order;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        ContentCachingRequestWrapper wrappedRequest = new ContentCachingRequestWrapper(request);

        int status;
        String trackingId = Optional.ofNullable(request.getHeader("trackingId")).orElse(UUID.randomUUID().toString().replaceAll("-", ""));

        MDC.put(TRACKING_KEY, trackingId);
        response.addHeader("trackingId", trackingId);

        // pass through filter chain to do the actual request handling
        filterChain.doFilter(wrappedRequest, response);

        status = response.getStatus();

        // only log request if there was an error
        if (status >= 400 || status < 200) {
            Map<String, Object> trace = getTrace(wrappedRequest, status);

            // body can only be read after the actual request handling was done!
            getBody(wrappedRequest, trace);
            logTrace(wrappedRequest, trace);
        }

        MDC.remove(TRACKING_KEY);
    }

    private void getBody(ContentCachingRequestWrapper request, Map<String, Object> trace) {
        // wrap request to make sure we can read the body of the request (otherwise it will be consumed by the actual
        // request handler)
        ContentCachingRequestWrapper wrapper = WebUtils.getNativeRequest(request, ContentCachingRequestWrapper.class);
        if (wrapper != null) {
            byte[] buf = wrapper.getContentAsByteArray();
            if (buf.length > 0) {
                String payload;
                try {
                    payload = new String(buf, 0, buf.length, wrapper.getCharacterEncoding());
                }
                catch (UnsupportedEncodingException ex) {
                    payload = "[unknown]";
                }

                trace.put("body", payload);
            }
        }
    }

    private void logTrace(HttpServletRequest request, Map<String, Object> trace) {
        Object method = trace.get("method");
        Object path = trace.get("path");
        Object statusCode = trace.get("statusCode");
        logger.error(String.format("%s %s produced an error status code '%s'. Trace: '%s'", method, path, statusCode, trace));
    }

    private Map<String, Object> getTrace(HttpServletRequest request, int status) {


        Map<String, Object> trace = new LinkedHashMap<String, Object>();
        trace.put("method", request.getMethod());
        trace.put("path", request.getRequestURI());
        trace.put("query", request.getQueryString());
        trace.put("statusCode", status);
        trace.put("remoteHost", request.getRemoteHost());
        trace.put("remoteIp", request.getRemoteAddr());
        trace.put("remotePort", request.getRemotePort());
        return trace;
    }

}
