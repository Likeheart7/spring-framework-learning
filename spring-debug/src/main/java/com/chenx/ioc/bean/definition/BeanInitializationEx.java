package com.chenx.ioc.bean.definition;

import com.chenx.ioc.bean.definition.pojo.Initialization;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;

public class BeanInitializationEx {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.register(BeanInitializationEx.class);
		context.refresh();

		/* 固定的顺序
		=====>>> postConstruct......
		=====>>> afterPropertiesSet......
		=====>>> @Bean参数指定的初始化方法......
		Initialization{}
		initMethod
		是延迟加载的吗：true
		=====>>> preDestroy......
		=====>>> @Bean参数指定的销毁方法
		 */
		Initialization bean = context.getBean(Initialization.class);
		System.out.println(bean);
		// 从BeanDefinition查看设置的初始化方法
		BeanDefinition initializationBeanDefinition = context.getBeanDefinition("initialization");
		// initMethod
		System.out.println(initializationBeanDefinition.getInitMethodName());
		// 是延迟加载的吗：true
		System.out.println("是延迟加载的吗：" + initializationBeanDefinition.isLazyInit());
		context.close();
	}
	// 如果标注了@Lazy注解，从BeanDefinition初始化Bean会延迟到Bean被访问的时候，也在这时候才执行各种初始化方法
	// xml中bean标签的lazy-init属性也是同理
	@Lazy
	@Bean(initMethod = "initMethod", destroyMethod = "destroyMethod")
	public Initialization initialization() {
		return new Initialization();
	}
}
