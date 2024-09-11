package com.chenx.ioc.lifecycle;


import com.chenx.ioc.dependency.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.util.ObjectUtils;


public class InstantiationAwareBeanPostProcessorEx {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		// 加载一个xml文件，用于测试postProcessAfterInstantiation的拦截
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(context);
		reader.loadBeanDefinitions("classpath:/xml/instantiation-aware-context.xml");
		context.getBeanFactory().addBeanPostProcessor(new InstantiationAwareBeanPostProcessorImpl());
		context.register(InstantiationAwareBeanPostProcessorEx.class);
		context.refresh();
		User user = context.getBean("user", User.class);
		User user2 = context.getBean("user2", User.class);
		// 可以看到，最终拿到的bean，实际上是被替换掉的。
		// User{id=99, name='InstantiationAwareBeanPostProcessor替换的User'}
		System.out.println(user);
		// 没被替换
		// User{id=2, name='原始User'}
		System.out.println(user2);
		// User{id=null, name='post拦截属性填充'}
		// 被InstantiationAwareBeanPostProcessor#postProcessAfterInstantiation拦截了属性填充
		System.out.println(context.getBean("userFromXml", User.class));
	}

	@Bean
	public User user() {
		return new User(1L, "原始User");
	}

	@Bean
	public User user2() {
		return new User(2L, "原始User");
	}

	static class InstantiationAwareBeanPostProcessorImpl implements InstantiationAwareBeanPostProcessor {

		/**
		 * 在bean初始化前，就会执行，如果返回值不是null，就直接用返回值替换原本要创建的bean
		 * 如果返回值是null，正常执行后续逻辑
		 */
		@Override
		public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
			if (ObjectUtils.nullSafeEquals("user", beanName)) {
				return new User(99L, "InstantiationAwareBeanPostProcessor替换的User");
			}
			return null; // 正常执行后续逻辑
		}

		/**
		 * 返回true，正常执行，返回false，不再执行后续属性填入逻辑
		 * 对@Bean注入的不生效，因为放进去的是在方法内创建好的已经填入属性的对象。
		 */
		@Override
		public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
			if ("userFromXml".equals(beanName) && bean.getClass().equals(User.class)) {
				User userFromXml = (User) bean;
				userFromXml.setName("post拦截属性填充");
				return false;
			}
			return true;
		}

		/**
		 * 在属性填充前后，可以拿到所有要填充的值，手动修改属性，对@Bean也有效。
		 * 但如果前面postProcessBeforeInstantiation或postProcessAfterInstantiation拦截过
		 * 该方法不会被执行
		 */
		@Override
		public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName) throws BeansException {
			if (("user2".equals(beanName) || "user".equals(beanName) || "userFromXml".equals(beanName)) && bean.getClass().equals(User.class)) {
				MutablePropertyValues mpv = (MutablePropertyValues) pvs;
				mpv.removePropertyValue("name");
				mpv.addPropertyValue("name", "by postProcessProperties");
			}
			return pvs;
		}

		/**
		 * 实例化后，初始化前，可以理解成initializingBean#afterProperties方法之前，注意@PostConstruct实际上就是本方法的某个实现处理的。
		 *
		 * @param bean     已经填充了属性的bean
		 * @param beanName bean名称
		 */
		@Override
		public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
			if ("user2".equals(beanName) && bean.getClass().equals(User.class)) {
				((User) bean).setName("postProcessBeforeInitialization");
			}
			return bean;
		}

		/**
		 * 初始化后，可以理解成initializingBean#afterProperties方法之后
		 *
		 * @param bean     已经填充了属性的bean
		 * @param beanName bean名称
		 */
		@Override
		public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
			return bean;
		}
	}
}
