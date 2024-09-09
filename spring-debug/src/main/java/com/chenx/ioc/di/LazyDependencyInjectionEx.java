package com.chenx.ioc.di;

import com.chenx.ioc.dependency.domain.User;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * 通过{@link Autowired} 和 {@link ObjectProvider}实现延迟依赖注入。
 */
public class LazyDependencyInjectionEx {

	@Autowired
	private ObjectProvider<User> users;

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(LazyDependencyInjectionEx.class);
		LazyDependencyInjectionEx bean = context.getBean(LazyDependencyInjectionEx.class);
		// 实际上拿到了所有User类型的bean
		bean.users.stream().forEach(System.out::println);
	}
	@Bean
	public User user1() {
		User user = new User();
		user.setName("user1");
		user.setId(1L);
		return user;
	}
	@Bean
	public User user2() {
		User user = new User();
		user.setName("user2");
		user.setId(2L);
		return user;
	}
}
