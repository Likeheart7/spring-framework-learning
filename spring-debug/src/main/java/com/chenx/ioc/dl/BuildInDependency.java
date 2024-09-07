package com.chenx.ioc.dl;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

/**
 * 关于Spring内置的依赖
 */
public class BuildInDependency {
	public static void main(String[] args) {
		// 该构造方法内执行了register和refresh
		/*
		 * org.springframework.context.annotation.internalConfigurationAnnotationProcessor ConfigurationClassPostProcessor类型，处理器@Configuration
		 * org.springframework.context.annotation.internalAutowiredAnnotationProcessor	AutowiredAnnotationBeanPostProcessor类型，处理@Autowired注解和@Value注解的BeanPostProcessor
		 * org.springframework.context.annotation.internalCommonAnnotationProcessor		CommonAnnotationBeanPostProcessor，处理jsr250的注解，如@PostConstruct
		 * org.springframework.context.event.internalEventListenerProcessor		处理事件监听相关
		 * org.springframework.context.event.internalEventListenerFactory		处理事件监听相关
		 * buildInDependency
		 * 这些自定义的bean注册的地方可以见org.springframework.context.annotation.AnnotationConfigUtils.registerAnnotationConfigProcessors(org.springframework.beans.factory.support.BeanDefinitionRegistry, java.lang.Object)
		 */
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(BuildInDependency.class);
		Arrays.stream(context.getBeanDefinitionNames()).forEach(System.out::println);
	}
}
