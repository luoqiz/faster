package org.wolf.security3.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;
import org.wolf.security3.utils.InMemoryStorage;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class ValidateCodeFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        logger.info(request.getRequestURI());
        logger.info(request.getMethod());
        if (StringUtils.pathEquals("/login", request.getRequestURI())
                && StringUtils.startsWithIgnoreCase(request.getMethod(), "post")) {
            validateCode(new ServletWebRequest(request));
        }
        filterChain.doFilter(request, response);
    }

    private void validateCode(ServletWebRequest servletWebRequest) throws ServletRequestBindingException {
        String validateCodeR = ServletRequestUtils.getStringParameter(servletWebRequest.getRequest(), "imageCode");
        String deviceId = ServletRequestUtils.getStringParameter(servletWebRequest.getRequest(), "deviceId");
        String code = (String) InMemoryStorage.map.get(deviceId);
        log.info("内存中保存的验证码是：{}", code);
        log.info("参数中的验证码是：{}", validateCodeR);
        if (code != null && validateCodeR != null && validateCodeR.equalsIgnoreCase(code)) {
            InMemoryStorage.map.remove(deviceId);
        }
    }
}
