package com.chenx.aop.overview;


import com.chenx.aop.overview.interceptor.InterceptorHandler;
import com.chenx.aop.overview.interceptor.ProxyRequest;
import com.chenx.aop.overview.interceptor.RealRequest;
import com.chenx.aop.overview.interceptor.Request;

import java.lang.reflect.Proxy;
import java.util.ArrayList;

/**
 * 拦截器的一个简单实现。也是 Spring MVC中拦截器的基本原理
 */
public class InterceptorEx {
	public static void main(String[] args) {
		RealRequest request = new RealRequest("/ask");
		ArrayList<InterceptorHandler> interceptorHandlers = new ArrayList<>();
		interceptorHandlers.add(new InterceptorHandler() {
			@Override
			public boolean preHandle() {
				System.out.println("拦截器1前置通知....");
				return true;
			}

			@Override
			public void postHandle() {
				System.out.println("拦截器1后置通知....");
			}
		});
		interceptorHandlers.add(new InterceptorHandler() {
			@Override
			public boolean preHandle() {
				System.out.println("拦截器2前置通知....");
				return true;
			}

			@Override
			public void postHandle() {
				System.out.println("拦截器2后置通知....");
			}
		});
		ProxyRequest proxy = new ProxyRequest(request, interceptorHandlers);
		Request proxiedRequest = (Request) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class<?>[]{Request.class}, proxy);
		proxiedRequest.execute();
		/*
		拦截器1前置通知....
		拦截器2前置通知....
		=====>>> 执行请求处理逻辑......
		拦截器2后置通知....
		拦截器1后置通知....
		 */
		// 或
		/*
		拦截器1前置通知....
		拦截器2前置通知....
		被拦截器 [com.chenx.aop.overview.InterceptorEx$2@7e774085] 拦截。不再执行后续逻辑
		 */
	}
}

