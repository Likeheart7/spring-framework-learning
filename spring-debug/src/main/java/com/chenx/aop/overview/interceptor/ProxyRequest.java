package com.chenx.aop.overview.interceptor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 * JDK动态代理类
 */
public class ProxyRequest implements InvocationHandler {
	// 被代理的目标对象
	private final Object target;
	private final ArrayList<InterceptorHandler> interceptorHandlers;

	public ProxyRequest(Object target, ArrayList<InterceptorHandler> interceptors) {
		this.target = target;
		// 传入拦截器，这里没做路径匹配逻辑
		this.interceptorHandlers = interceptors;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		// 方法执行前调用所有前置处理
		// 如果有前置处理返回false，直接结束，不执行后续逻辑
        for (InterceptorHandler interceptorHandler : this.interceptorHandlers) {
            if (!interceptorHandler.preHandle()) {
				System.out.println("被拦截器 [" + interceptorHandler + "] 拦截。不再执行后续逻辑");
				return null;
			}
        }
		Object result = method.invoke(target, args);
		// 方法执行后调用所有后置处理
		for (int i = this.interceptorHandlers.size() - 1; i >= 0; i--) {
			this.interceptorHandlers.get(i).postHandle();
		}
		return result;
	}
}
