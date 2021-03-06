package com.livk.sso.resoure.filter;

import com.livk.sso.resoure.config.RsaKeyProperties;
import com.livk.sso.resoure.entity.User;
import com.livk.sso.util.JwtUtils;
import com.livk.util.ResponseUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import java.io.IOException;
import java.util.Map;

/**
 * <p>
 * TokenVerifyFilter
 * </p>
 *
 * @author livk
 * @date 2022/4/11
 */
public class TokenVerifyFilter extends BasicAuthenticationFilter {

    private final RsaKeyProperties properties;

    public TokenVerifyFilter(AuthenticationManager authenticationManager, RsaKeyProperties properties) {
        super(authenticationManager);
        this.properties = properties;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        var authorization = request.getHeader("Authorization");
        if (authorization != null && authorization.startsWith("Bearer ")) {
            var token = authorization.replaceFirst("Bearer ", "");
            var payload = JwtUtils.getInfo(token, properties.getPublicKey(), User.class);
            var user = payload.getUserInfo();
            if (user != null) {
                var authenticationToken = new UsernamePasswordAuthenticationToken(user.getUsername(),
                        user.getPassword(), user.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                chain.doFilter(request, response);
            } else {
                var map = Map.of("code", HttpServletResponse.SC_FORBIDDEN, "msg", "??????????????????");
                ResponseUtils.out(response, map);
            }
        } else {
            var map = Map.of("code", HttpServletResponse.SC_FORBIDDEN, "msg", "????????????");
            ResponseUtils.out(response, map);
        }
    }

}
