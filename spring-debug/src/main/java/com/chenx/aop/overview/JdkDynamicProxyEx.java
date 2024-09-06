package com.chenx.aop.overview;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 简单的JDK动态代理实现
 * JDK动态代理生成的类的名称格式是：class com.sun.proxy.$Proxy0 extends java.lang.reflect.Proxy implements Xxx
 */
public class JdkDynamicProxyEx {

	public static void main(String[] args) {
		ProxyClass proxyInstance = new ProxyClass(new Proxied());
		Echo echoService =  proxyInstance.getProxy();
		/*
		前置通知...
		Proxied echo message.
		后置通知...
		 */
		echoService.echo();
	}

	static class ProxyClass implements InvocationHandler {
		private final Proxied target;
		public ProxyClass(Proxied proxied) {
			target = proxied;
		}
		@Override
		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
			System.out.println("前置通知...");
			Object result = method.invoke(target);
			System.out.println("后置通知...");
			return result;
		}

		public Echo getProxy() {
			return (Echo) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), this.target.getClass().getInterfaces(), this);
		}
	}

	static class Proxied implements Echo {
		@Override
		public void echo() {
			System.out.println("Proxied echo message.");
		}
	}

	interface Echo {
		void echo();
	}
}
