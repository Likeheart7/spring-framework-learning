package com.chenx.ioc.dependency.injection;

import com.chenx.ioc.dependency.injection.repository.UserRepository;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.Environment;

/**
 * <pre>
 * 依赖注入
 * 依赖来源有以下三种：
 * 1. 自定义Bean，可以注入可以查找
 * 2. 内建依赖：可以注入，查找不到，如ApplicationContext，BeanFactory
 * 3. 内建Bean，可以注入可以查找，如StandardEnvironment
 * </pre>
 */
public class DependencyInjection {
	public static void main(String[] args) {
		// 配置文件中通过bean的autowire属性，配置对bean属性的自动注入
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/xml/dependency-injection-context.xml");
		// 自定义bean，可以注入可以查找
		UserRepository userRepository = applicationContext.getBean("userRepository", UserRepository.class);
		// UserRepository{userList=[User{id=29597, name='岑参'}, Administrator{id=11111, name='管理员陈生', Permissions='1'}]}
		System.out.println(userRepository);
		// 会对 （内建依赖）自动注入
		BeanFactory beanFactory = userRepository.getBeanFactory();
		// 结论，注入进来了，但是不是同一个
		System.out.println(beanFactory);
		System.out.println("和我们拿到的是否是同一个BeanFactory：" + (beanFactory == applicationContext));
		// 那么他应该是哪里的呢？
		// 结论：和ApplicationContext内部的是同一个
		System.out.println("和我们拿到的内部的BeanFactory是不是同一个：" + (beanFactory == applicationContext.getBeanFactory()));
		// 但是实际上我们通过自己依赖查找的方式，是获取不到这个bean的
		// 输出报错：No qualifying bean of type 'org.springframework.beans.factory.BeanFactory' available
		//        No qualifying bean of type 'org.springframework.context.ApplicationContext' available
		// 所以依赖查找和依赖注入的来源一定是不同的
//		System.out.println(applicationContext.getBean(BeanFactory.class));
//		System.out.println(applicationContext.getBean(ApplicationContext.class));

		//我们并没有注入ObjectFactory<ApplicationContext>的bean
		// 但是获取到了，而且其内部，有我们使用的ApplicationContext
		ObjectFactory<ApplicationContext> objectFactory = userRepository.getApplicationContext();
		System.out.println(objectFactory.getObject() == applicationContext); // true

		// 容器 （内建Bean），非用户注册但是获取得到
		// StandardEnvironment {activeProfiles=[], defaultProfiles=[default], propertySources=[PropertiesPropertySource {name='systemProperties'}, SystemEnvironmentPropertySource {name='systemEnvironment'}]}
		System.out.println(beanFactory.getBean(Environment.class));
	}
}
