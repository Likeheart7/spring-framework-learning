package com.chenx.beans;

import java.beans.BeanDescriptor;
import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.lang.reflect.Method;
import java.util.stream.Stream;

/**
 * {@link  java.beans.BeanInfo}
 * 这里主要是描述Java Beans的内容，与Spring无关。
 * 通过Java内省机制可以获取BeanInfo，BeanInfo中包含了Bean的几乎所有信息
 */
public class BeanInfoEx {
	public static void main(String[] args) throws IntrospectionException {
		// 通过Java的自省获取bean信息
		// 实际上是通过get/set方法获取的，所以Object中的getClass会被认为是一个属性，第二个参数stopClass表示忽略这个父类
		BeanInfo beanInfo = Introspector.getBeanInfo(Person.class, Object.class);
		/*
		beanInfo中包含了许多信息
		 */
		Stream.of(beanInfo.getPropertyDescriptors()).forEach(System.out::println);
		Person person = new Person();

		// class com.chenx.beans.Person ///// Person
		BeanDescriptor beanDescriptor = beanInfo.getBeanDescriptor();
		Class<?> beanClass = beanDescriptor.getBeanClass();
		String beanName = beanDescriptor.getName();
		System.out.println(beanClass + " ///// " + beanName);

		/*
		class java.lang.Integer age = null
		class java.lang.String name = null
		 */
		Stream.of(beanInfo.getPropertyDescriptors()).forEach(propertyDescriptor -> {
			Class<?> propertyType = propertyDescriptor.getPropertyType();
			String propertyName = propertyDescriptor.getName();
			Method readMethod = propertyDescriptor.getReadMethod();
			Method writeMethod = propertyDescriptor.getWriteMethod();
			try {
				System.out.println(propertyType + " " + propertyName + " = " + readMethod.invoke(person));
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		});
	}
}
