package com.chenx.ioc.di;

import com.chenx.ioc.dependency.domain.User;
import com.chenx.ioc.pojo.UserHolder;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * 基于注解的自动注入
 */
public class AnnotationDependencyInjectionEx {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		// 1.先加载xml拿到User类型的bean
		// 通过reader可以在注解驱动的情况下，为其上下文加载xml定义
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(context);
		// 这里主要是为了拿到xml中定义的user类型的bean
		reader.loadBeanDefinitions("classpath:/xml/xml-autowiring-context.xml");
		// 2. 再注解驱动拿到依赖于User类型的bean的UserHolder类型的bean。否则会因为找不到User类型的bean抛出异常
		context.register(AnnotationDependencyInjectionEx.class);
		context.refresh();

		UserHolder userHolder = context.getBean("customUserHolder", UserHolder.class);
		// UserHolder{user=User{id=10, name='陈新'}}
		System.out.println(userHolder);
	}

	/**
	 * 该方法名会作为bean的名称，spring会注入该方法的参数user
	 */
	@Bean
	public UserHolder customUserHolder(User user){
		return new UserHolder(user);
	}
}
