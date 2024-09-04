package com.chenx.ioc.dl;

import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * {@link  org.springframework.beans.factory.HierarchicalBeanFactory} 层次BeanFactory，即可以有父BeanFactory
 */
public class HierarchicalDLEx {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.register(HierarchicalDLEx.class);
		context.refresh();
		AnnotationConfigApplicationContext parentContext = new AnnotationConfigApplicationContext();
		parentContext.register(ParentBeanFactoryConfig.class);
		parentContext.refresh();
		// 设置父BeanFactory
		context.getBeanFactory().setParentBeanFactory(parentContext);
		// localBean就是本容器有的Bean
		System.out.println(context.containsLocalBean("customObject")); // false
		System.out.println(parentContext.containsLocalBean("customObject")); //true
		// 通过context.getBean可以获取到父BeanFactory中的bean，如果同名，以自己优先
		System.out.println(context.getBean("hello"));
	}

	@Bean
	public String hello() {
		return "Hi, Hello";
	}


}
