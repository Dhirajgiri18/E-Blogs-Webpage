package com.reljicd.config;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Custom 403 access denied handler
 */
@Component
public class MyAccessDeniedHandler implements AccessDeniedHandler {

    private static Logger logger = LoggerFactory.getLogger(MyAccessDeniedHandler.class);

    @Override
    public void handle(HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse,
            AccessDeniedException e) throws IOException {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth != null) {
            logger.info("User '{}' attempted to access the protected URL: {}", auth.getName(),
                    httpServletRequest.getRequestURI());
        } else {
            logger.warn("Anonymous user attempted to access the protected URL: {}", httpServletRequest.getRequestURI());
        }

        httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/403");
    }
}
