package com.chenx.aop.overview.interceptor;

/**
 * 拦截器顶级接口
 */
public interface InterceptorHandler{
	default boolean preHandle() {
		return true;
	}
	default void postHandle() {
	}
}
