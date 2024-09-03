package com.chenx.ioc.bean.definition;

import com.chenx.ioc.bean.definition.pojo.Instance;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanInstantiationEx {
	public static void main(String[] args) throws Exception {
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/xml/bean-instantiation-context.xml");
		// 1. 通过静态方法配置的
		Instance byStaticMethod = applicationContext.getBean("by-static-method", Instance.class);
		System.out.println(byStaticMethod);
		// 2. 通过实例方法配置的
		Instance byInstanceMethod = applicationContext.getBean("by-instance-method", Instance.class);
		System.out.println(byInstanceMethod);
		// 3. 通过FactoryBean配置的
		Instance byFactoryBean = applicationContext.getBean("by-factory-bean", Instance.class);
		System.out.println(byFactoryBean);
		/*
		Instance{origin='静态方法'}
		Instance{origin='实例方法'}
		Instance{origin='来自FactoryBean'}
		 */

		// 通过手动配置BeanDefinition
		GenericBeanDefinition instanceBeanDefinition = new GenericBeanDefinition();
		instanceBeanDefinition.setBeanClass(Instance.class);
		// 通过MutablePropertyValues批量操作属性
		MutablePropertyValues propertyValues = new MutablePropertyValues();
		propertyValues.addPropertyValue("origin", "手动配置BeanDefinition");
		instanceBeanDefinition.setPropertyValues(propertyValues);
		DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory) applicationContext.getBeanFactory();
		beanFactory.registerBeanDefinition("instance", instanceBeanDefinition);
		// 能够获取到 Instance{origin='手动配置BeanDefinition'}
		System.out.println(applicationContext.getBean("instance"));
		// 但是只通过类型获取不到
//		System.out.println(applicationContext.getBean(Instance.class));

		// 通过手动注册单例
		Instance instance = new Instance();
		instance.setOrigin("手动注册的单例Instance");
		beanFactory.registerSingleton("manualInstance", instance);
		// Instance{origin='手动注册的单例Instance'}
		System.out.println(applicationContext.getBean("manualInstance", Instance.class));
	}
}
