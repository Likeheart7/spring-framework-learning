package com.chenx.ioc.di;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * 基于XML方式的手动注入，核心是通过bean标签下的property标签的的ref属性，手动指向一个其他的bean
 */
public class ApiDependencyInjectionEx {
	public static void main(String[] args) {
		// 创建beanFactory
		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
		// 指定reader绑定beanFactory
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
		// 加载指定xml文件
		reader.loadBeanDefinitions("classpath:/xml/xml-autowiring-context.xml");
		UserHolder bean = beanFactory.getBean(UserHolder.class);
		// UserHolder{user=User{id=10, name='陈新'}}
		System.out.println(bean);
	}
}
