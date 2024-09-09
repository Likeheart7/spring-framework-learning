package com.chenx.ioc.di;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * 通过{@link PropertySource} 和 {@link Value} 实现外部配置绑定
 */
@Configuration // 不加这个注解，找不到配置
@PropertySource(value = "classpath:/properties/default.properties", encoding = "UTF-8") // encoding解决编码问题
public class ExternalDependencyEx {
	@Value("${usr.name:chen}")
	private String name;
	@Value("${usr.id:-1}")
	private String id;

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ExternalDependencyEx.class);
		ExternalDependencyEx currentBean = context.getBean(ExternalDependencyEx.class);
		/*
		user.id = 29
		user.name = 陈生
		 */
		System.out.println("user.id = " + currentBean.id);
		System.out.println("user.name = " + currentBean.name);
	}
}
