package com.chenx.ioc.di;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 自定义ResolveDependency只支持基于类型的依赖注入
 */
public class ResolveDependencyEx {
	@Autowired
	private String hello;

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.register(ResolveDependencyEx.class);
		context.addBeanFactoryPostProcessor(beanFactory -> {
			beanFactory.registerResolvableDependency(String.class, "hello, hi, hey");
		});
		context.refresh();
		ResolveDependencyEx currentBean = context.getBean(ResolveDependencyEx.class);
		System.out.println(currentBean.hello); // hello, hi, hey

//		System.out.println(context.getBean(String.class)); // NoSuchBeanDefinitionException

	}
}
