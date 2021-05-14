package com.apifan.common.commonrandomapi.filter;

import com.google.common.base.Joiner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Map;
import java.util.TreeMap;

/**
 * API日志过滤器
 */
@Component
public class ApiLogFilter extends OncePerRequestFilter {
    private static final Logger logger = LoggerFactory.getLogger(ApiLogFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        long begin = System.currentTimeMillis();
        filterChain.doFilter(request, response);
        long cost = System.currentTimeMillis() - begin;
        logger.info("{}请求 {}, 参数: {}, 耗时 {} 毫秒", request.getMethod(), request.getRequestURI(), formatAllParams(request), cost);
    }

    /**
     * 格式化所有请求参数
     *
     * @param request
     * @return
     */
    private static String formatAllParams(HttpServletRequest request) {
        if (request == null) {
            return null;
        }
        Map<String, String> paramsMap = new TreeMap<>();
        Enumeration<String> parameterNames = request.getParameterNames();
        if (parameterNames != null) {
            while (parameterNames.hasMoreElements()) {
                String name = parameterNames.nextElement();
                paramsMap.put(name, request.getParameter(name));
            }
        }
        return Joiner.on("&").withKeyValueSeparator("=").join(paramsMap);
    }
}
