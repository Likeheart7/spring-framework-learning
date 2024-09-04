package com.chenx.ioc.dl;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * {@link org.springframework.beans.factory.ObjectProvider} 进行依赖查找
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
		// 前提是该类型只有一个，如果有多个，会抛出 NoUniqueBeanDefinitionException:No qualifying bean of type 'java.lang.String' available: expected single matching bean but found 2: hello,hi
		ObjectProvider<String> provider = context.getBeanProvider(String.class);
		System.out.println(provider.getObject());
	}

	@Bean
	public String hello() {
		return "Hei, Hi, Hello";
	}
//	@Bean
//	public String hi() {
//		return "Hi, Hi, Hi";
//	}
}
