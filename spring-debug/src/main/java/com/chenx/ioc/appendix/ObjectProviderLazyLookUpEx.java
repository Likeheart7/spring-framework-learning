package com.chenx.ioc.appendix;

import com.chenx.ioc.appendix.pojo.Example;
import com.chenx.ioc.dependency.domain.User;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;

/**
 * 为什么说ObjectProvider/ObjectFactory是延迟查找
 * 因为当调用getObject方法的时候，才实际去找该bean，如果他是lazy的，那么调用getObject才触发它的初始化.
 */
public class ObjectProviderLazyLookUpEx {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ObjectProviderLazyLookUpEx.class);
		ObjectProvider<Example> exampleProvider = context.getBeanProvider(Example.class);
		// 如果一个bean是lazy的，那么我获取他的ObjectProvider，并不会触发初始化，当调用getObject方法时才会触发。
		System.out.println(exampleProvider.getObject());
		// 即使尝试去获取一个不存在的Bean的ObjectProvider，也是在getObject方法调用时，才会报NoSuchBeanDefinitionException
		ObjectProvider<User> userProvider = context.getBeanProvider(User.class);
//		System.out.println(userProvider.getObject());
	}

	@Bean
	@Lazy
	public Example example() {
		return new Example("example", "this is a simple example.");
	}
}
