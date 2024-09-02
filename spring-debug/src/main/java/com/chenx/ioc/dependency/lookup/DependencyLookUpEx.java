package com.chenx.ioc.dependency.lookup;

import com.chenx.ioc.dependency.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Map;

/**
 * 依赖查找
 */
public class DependencyLookUpEx {
	public static void main(String[] args) {
		BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/xml/dependency-lookup-context.xml");
		/*
		User{id=29597, name='岑参'}
		User{id=95297, name='岑参'}
		 */
		lookupInRealTime(beanFactory);
		lookupInLazy(beanFactory);
		// 根据类型查找，多个同类型会报错，可以通过primary属性指定优先的
		lookupByType(beanFactory);
		lookupByTypeAndName(beanFactory);
		// 根据集合类型查找
		lookupCollectionByType(beanFactory);
		// 根据注解查找，需要时ListableBeanFactory
		lookupByAnnotationType(beanFactory);
	}

	private static void lookupByAnnotationType(BeanFactory beanFactory) {
		if (beanFactory instanceof ListableBeanFactory) {
			ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
			Map<String, Object> beans = listableBeanFactory.getBeansWithAnnotation(Component.class);
			// 根据注解查找：{user=User{id=95297, name='岑参'}, administrator=Administrator{id=11111, name='管理员陈生', Permissions='1'}}
			// 被@Component修饰的其他注解也算，如@Controller
			System.out.println("根据注解查找：" + beans);
		}
	}

	/**
	 * 根据类型查找所有结果的Map
	 */
	private static void lookupCollectionByType(BeanFactory beanFactory) {
		if (beanFactory instanceof ListableBeanFactory) {
			ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
			// 键是bean名称，value是对应实例
			Map<String, User> beansOfType = listableBeanFactory.getBeansOfType(User.class);
			// {user=User{id=95297, name='岑参'}, administrator=Administrator{id=11111, name='管理员陈生', Permissions='1'}}
			System.out.println(beansOfType);
		}
	}

	/**
	 * 根据名称和类型查找
	 */
	private static void lookupByTypeAndName(BeanFactory beanFactory) {
		User user = beanFactory.getBean("administrator", User.class);
		System.out.println("根据名称和类型查找：" + user);
	}

	/**
	 * 根据类型查找
	 */
	private static void lookupByType(BeanFactory beanFactory) {
		User user = beanFactory.getBean(User.class);
		System.out.println("根据类型查找：" + user);
	}

	/**
	 * 实时查找，直接获取对应bean
	 */
	private static void lookupInRealTime(BeanFactory beanFactory) {
		User user = (User) beanFactory.getBean("user");
		System.out.println(user);
		user.setId(95297L);
	}

	/**
	 * 通过ObjectFactory延迟查找，这里是ObjectFactoryCreatingFactoryBean实例，内部的targetBeanName持有一个bean实例，通过父getObject方法获取
	 */
	@SuppressWarnings("unchecked")
	private static void lookupInLazy(BeanFactory beanFactory) {
		ObjectFactory<User> objectFactory = (ObjectFactory<User>) beanFactory.getBean("objectFactory");
		System.out.println(objectFactory.getObject());
	}
}
