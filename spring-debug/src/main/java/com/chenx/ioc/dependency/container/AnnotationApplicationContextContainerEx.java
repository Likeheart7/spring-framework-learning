package com.chenx.ioc.dependency.container;

import com.chenx.ioc.dependency.domain.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * 基于注解的ApplicationContext IOC容器
 * ApplicationContext在BeanFactory的基础上，提供了基于注解配置Bean的功能，只依靠BeanFactory无法基于注解驱动。
 */
public class AnnotationApplicationContextContainerEx {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
		applicationContext.register(AnnotationApplicationContextContainerEx.class);
		// 容器初始化
		applicationContext.refresh();
		// 依赖查找
		/* User{id=2200, name='JetBrains'} */
		System.out.println(applicationContext.getBean(User.class));
	}

	@Bean
	public User user() {
		User user = new User();
		user.setId(2200L);
		user.setName("JetBrains");
		return user;
	}
}
