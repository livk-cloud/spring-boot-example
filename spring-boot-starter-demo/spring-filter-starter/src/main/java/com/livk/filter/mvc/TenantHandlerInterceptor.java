package com.livk.filter.mvc;

import com.livk.filter.context.TenantContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Nonnull;

/**
 * <p>
 * TenantHandlerInterceptor
 * </p>
 *
 * @author livk
 * @date 2022/5/10
 */
public class TenantHandlerInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, @Nonnull HttpServletResponse response, @Nonnull Object handler)
			throws Exception {
		TenantContext.setTenantId(request.getHeader(TenantContext.ATTRIBUTES));
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}

	@Override
	public void afterCompletion(@Nonnull HttpServletRequest request, @Nonnull HttpServletResponse response,
			@Nonnull Object handler, Exception ex) {
		TenantContext.remove();
	}

}
