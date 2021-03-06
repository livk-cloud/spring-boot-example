package com.livk.security.filter;

import com.livk.security.support.AuthenticationContext;
import com.livk.util.ResponseUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Map;

/**
 * <p>
 * AuthorizationTokenFilter
 * </p>
 *
 * @author livk
 * @date 2022/3/23
 */
@Slf4j
@Component
public class AuthorizationTokenFilter extends OncePerRequestFilter {

    @Value("${permitAll.url}")
    private String[] permitAllUrl;

    @Override
    protected void doFilterInternal(HttpServletRequest request, @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {
        if (!ArrayUtils.contains(permitAllUrl, request.getRequestURI())) {
            var token = request.getHeader("Authorization");
            if (StringUtils.hasText(token)) {
                // 切换为redis
                var authentication = AuthenticationContext.getAuthentication(token);
                if (authentication == null) {
                    ResponseUtils.out(response,
                            Map.of("code", HttpStatus.FORBIDDEN.value(), "msg", "token not available"));
                } else {
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    filterChain.doFilter(request, response);
                }
            } else {
                ResponseUtils.out(response, Map.of("code", HttpStatus.FORBIDDEN.value(), "msg", "missing token"));
            }
        } else {
            filterChain.doFilter(request, response);
        }
    }

}
