package com.chenx.ioc.di;

import com.chenx.ioc.dependency.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.AnnotationConfigUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ResourceLoader;

import javax.annotation.Resource;


/**
 * <h3>关于依赖注入和依赖查找的来源问题，以及为什么依赖注入的范围比依赖查找更大。</h3>
 * <h4>依赖查找</h4>
 * <pre>
 *     首先进入依赖查找的getBean方法来看，实际上是调用了getBeanFactory().getBean(xxx)来查找
 *     那么，实际上就是通过DefaultListableBeanFactory#getBean查找，最终调用resolveBean方法
 * </pre>
 * <h4>依赖注入</h4>
 * <pre>
 *     对于@Autowired（@Inject和他一样）和@Resource，分别由
 *     {@link org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor}
 * 	   和{@link org.springframework.context.annotation.CommonAnnotationBeanPostProcessor}处理，
 * 	   在@Resource没能匹配同名bean的情况下，和@Autowire的结果一样，都会走DefaultListableBeanFactory#resolveDependency()
 * 	   最终是遍历resolvableDependencies属性中所有容器来处理依赖，那么resolvableDependencies属性的内容就是依赖注入的来源
 * 	   而实际上resolvableDependencies在{@link org.springframework.context.support.AbstractApplicationContext#prepareBeanFactory(ConfigurableListableBeanFactory) }
 * 	   中设置了。实际上就是BeanFactory和ApplicationContext两个对象
 * </pre>
 * <h4>{@link AnnotationConfigUtils#registerAnnotationConfigProcessors(BeanDefinitionRegistry, Object)}方法中，注册了AutowiredAnnotationBeanPostProcessor和CommonAnnotationBeanPostprocessor</h4>
 */
public class DependencySourceEx {

	// 根据AbstractApplicationContext#prepareBeanFactory方法，依赖注入依靠四个对象
	@Autowired
	private BeanFactory autowiredBeanFactory;
	// 后面三个都是同一个对象
	@Autowired
	private ApplicationContext applicationContext;
	@Autowired
	private ResourceLoader resourceLoader;
	@Autowired
	private ApplicationEventPublisher applicationEventPublisher;

	// @Resource优先名称导致异常的问题。
//	@Resource
//	private User user;

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DependencySourceEx.class);
//		User dlUser = context.getBean("user", User.class); // 依赖查找
//		System.out.println(dlUser);

		ConfigurableListableBeanFactory beanFactory = context.getBeanFactory(); // 从ApplicationContext中获取的BeanFactory

		DependencySourceEx currentBean = context.getBean(DependencySourceEx.class);
		System.out.println(beanFactory == currentBean.autowiredBeanFactory); //true
		System.out.println(context == currentBean.applicationContext);	// true
		System.out.println(context == currentBean.resourceLoader);	// true
		System.out.println(context == currentBean.applicationEventPublisher); // true

//		System.out.println(currentBean.user);
	}

//	@Bean
//	public User str() {
//		User user = new User();
//		user.setId(1L);
//		user.setName("陈生");
//		return user;
//	}
//	@Bean
//	public String user() {
//		return "user";
//	}
}
