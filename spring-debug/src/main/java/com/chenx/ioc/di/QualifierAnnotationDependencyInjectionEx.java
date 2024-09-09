package com.chenx.ioc.di;

import com.chenx.ioc.dependency.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;

/**
 * {@link org.springframework.beans.factory.annotation.Qualifier} 注解除了能用来限定名称以外，实际上还可以用来分组
 */
public class QualifierAnnotationDependencyInjectionEx {

	@Autowired // 会先基于类型再基于名称注入，如果都无法确定唯一的bean，会抛出异常
	@Qualifier("user1")
	private User user2;
	@Resource	// 先基于名称再基于类型注入
	private User user;

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(QualifierAnnotationDependencyInjectionEx.class);
		QualifierAnnotationDependencyInjectionEx bean = context.getBean(QualifierAnnotationDependencyInjectionEx.class);
		System.out.println(bean.user2);
		System.out.println(bean.user);
	}

	@Bean
	public User user1() {
		User user = new User();
		user.setId(1L);
		user.setName("user1");
		return user;
	}
//	@Bean
//	public User user2() {
//		User user = new User();
//		user.setId(2L);
//		user.setName("user2");
//		return user;
//	}
}
