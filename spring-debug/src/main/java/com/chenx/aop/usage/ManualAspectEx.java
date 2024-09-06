package com.chenx.aop.usage;

import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class ManualAspectEx {
	public static void main(String[] args) {
		// 被代理对象
		HashMap<String, String> proxied = new HashMap<>();
		// 创建代理工厂，配置被代理对象
		AspectJProxyFactory proxyFactory = new AspectJProxyFactory(proxied);
		// 设置代理配置
		proxyFactory.addAspect(AspectConfig.class);
		proxyFactory.addAdvice(new MethodBeforeAdvice() {
			@Override
			public void before(Method method, Object[] args, Object target) throws Throwable {
				if ("put".equals(method.getName())) {
					System.out.printf("存入Map的键为 %s, 值为 %s ",args[0], args[1]);
				}
			}
		});
		// 获取代理对象
		Map<String,String> proxy = proxyFactory.getProxy();
		// 存入Map的键为 chen, 值为 生
		proxy.put("chen", "生");
	}
}
