package com.chenx.ioc.di;

import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Map;

/**
 * 基于XML方式的手动注入，核心是通过bean标签下的property标签的的ref属性，手动指向一个其他的bean
 */
public class XmlDependencyInjectionEx {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		// 1.先加载xml拿到User类型的bean
		// 通过reader可以在注解驱动的情况下，为其上下文加载xml定义
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(context);
		// 这里主要是为了拿到xml中定义的user类型的bean
		reader.loadBeanDefinitions("classpath:/xml/xml-autowiring-context.xml");
		// 2. 通过手动注册BeanDefinition来配置一个Bean的信息，容器会为我们生成Bean的实例
		BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(UserHolder.class);
		builder.addPropertyReference("user", "user"); // 添加手动注入依赖
//		builder.addConstructorArgReference("user"); // 手动通过构造器注入依赖，因为是byType的，所以不用指定名称
		AbstractBeanDefinition beanDefinition = builder.getBeanDefinition();
		context.registerBeanDefinition("myUserHolder", beanDefinition);
		// 3.启动容器
		context.refresh();
		Map<String, UserHolder> userHolderMap = context.getBeansOfType(UserHolder.class);
		/*
		第一个是xml文件中的bean标签配置的，默认名称是全类名#0
		第二个是bean标签通过autowire注入属性的
		第三个是上面通过注册BeanDefinition生成的
		这三种都是setter注入，都走了setter方法的逻辑
		=====>>> setter......
		=====>>> setter......
		=====>>> setter......
		{
			com.chenx.ioc.di.UserHolder#0=UserHolder{user=User{id=10, name='陈新'}},
			autowiredUserHolder=UserHolder{user=User{id=10, name='陈新'}},
			 myUserHolder=UserHolder{user=User{id=10, name='陈新'}}
		 }
		 */
		System.out.println(userHolderMap);
	}
}
