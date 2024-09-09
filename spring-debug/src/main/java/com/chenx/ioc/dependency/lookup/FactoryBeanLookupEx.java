package com.chenx.ioc.dependency.lookup;

import com.chenx.ioc.dependency.domain.User;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.support.FactoryBeanRegistrySupport;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

/**
 * <pre>
 * {@link  org.springframework.beans.factory.FactoryBean} 创建的Bean
 * 一个是正常名称规则的，getObject方法的返回值，另一个是在名称前加一个&号的FactoryBean的实例
 * 并且getObject方法作为Bean的返回值，实际上放在{@link FactoryBeanRegistrySupport#factoryBeanObjectCache}属性中，而不是singletonObjects
 * </pre>
 */
public class FactoryBeanLookupEx {
	public static void main(String[] args) throws Exception {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.register(FactoryBeanLookupEx.class);
		context.refresh();
		// class com.chenx.ioc.dependency.domain.User
		// 所以说放入IOC容器的不是FactoryBean类型，而是其getObject方法的返回值，而且该单例在factoryBeanObjectCache中
		// 第一次调用getBean,会通过getObject获取实际对象, 然后将其放入factoryBeanObjectCache中,以后就从中取
		System.out.println(context.getBean("factoryUser").getClass());	// 获取的是factoryBeanObjectCache中的bean
		System.out.println(context.getBean("&factoryUser").getClass());	// 获取的是singletonObjects中的bean
		// 如果要获取FactoryBean实例，需要在前面加个&
		Object object = context.getBean("&factoryUser");
		System.out.println(object instanceof FactoryBean); // true
		// User{id=10001, name='factory User'}
        System.out.println(((FactoryBean) object).getObject());
		// 但是实际上，单例池singletonObjects中没有&开头的单例，但是就是可以获取到
		System.out.println(Arrays.toString(context.getBeanFactory().getSingletonNames()));
	}

	@Bean
	public FactoryBean<User> factoryUser() {
		return new FactoryBean<User>() {
			@Override
			public User getObject() throws Exception {
				User user = new User();
				user.setName("factory User");
				user.setId(10001L);
				return user;
			}

			@Override
			public Class<?> getObjectType() {
				return User.class;
			}
		};
	}

}
