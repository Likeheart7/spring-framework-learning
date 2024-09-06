package com.chenx.aop.usage;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy
public class AspectjPointcutEx {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		// 注册AspectJ配置
		context.register(AspectjPointcutEx.class, AspectConfig.class);
		context.refresh();
		AspectjPointcutEx bean = context.getBean(AspectjPointcutEx.class);
		/*
		=====>>> Around advisor......
		=====>>> before advisor......
		execution......
		=====>>> after advisor......
		=====>>> Around advisor......
		 */
		bean.execution();
	}

	public void execution() {
		System.out.println("execution......");
	}

}
