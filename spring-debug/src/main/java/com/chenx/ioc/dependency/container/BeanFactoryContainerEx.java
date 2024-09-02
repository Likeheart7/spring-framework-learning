package com.chenx.ioc.dependency.container;

import com.chenx.ioc.dependency.domain.User;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * 基于XML的BeanFactory IoC容器
 */
public class BeanFactoryContainerEx {
	public static void main(String[] args) {
		// ioc容器
		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
		// 向IoC容器加载bean的类
		XmlBeanDefinitionReader definitionReader = new XmlBeanDefinitionReader(beanFactory);
		int definitionCount = definitionReader.loadBeanDefinitions("classpath:/xml/dependency-lookup-context.xml");
		/*
		查找到的Bean数量为：3
		Administrator{id=11111, name='管理员陈生', Permissions='1'}
		 */
		System.out.println("查找到的Bean数量为：" + definitionCount);
		// 依赖查找
		System.out.println(beanFactory.getBean(User.class));
	}
}
