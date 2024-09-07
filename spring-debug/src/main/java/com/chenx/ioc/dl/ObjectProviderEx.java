package com.chenx.ioc.dl;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * {@link org.springframework.beans.factory.ObjectProvider} 进行依赖查找
 * ObjectProvider就是ObjectFactory的一个子接口，同时继承了Iterable
 * 可以实现延迟查找的功能
 */
public class ObjectProviderEx {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.register(ObjectProviderEx.class);
		context.refresh();
		lookupWithProvider(context);
		context.close();
	}

	private static void lookupWithProvider(ApplicationContext context) {
		// getBeanProvider提供返回一个包装了获取到的Bean的ObjectProvider
		ObjectProvider<String> provider = context.getBeanProvider(String.class);
		// 调用getObject()前提是该类型只有一个，如果有多个，会抛出
		// NoUniqueBeanDefinitionException:No qualifying bean of type 'java.lang.String' available: expected single matching bean but found 2: hello,hi
		System.out.println(provider.getObject());
		// 如果有多个，可以迭代处理
		/*
		Hei, Hi, Hello
		Hi, Hi, Hi
		 */
		provider.stream().forEach(System.out::println);
	}

	@Bean
	public String hello() {
		return "Hei, Hi, Hello";
	}
	@Bean
	public String hi() {
		return "Hi, Hi, Hi";
	}
}
