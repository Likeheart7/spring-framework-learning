package com.chenx.ioc.di;

import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.beans.factory.config.DependencyDescriptor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.CommonAnnotationBeanPostProcessor;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.util.Map;

/**
 * <pre>
 * <h4>debug一下为什么@Autowired是先类型再名称，@Resource为什么是先名称再类型。</h4>
 * <p><em>仅分析字段注入</em></p>
 *
 * {@link Autowired} 由 {@link org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor}处理,<br/>
 * 		处理入口是{@link AutowiredAnnotationBeanPostProcessor.AutowiredFieldElement#resolveFieldValue(Field, Object, String)}<br/>
 * 		根据根据名称从多个同类型中取出最合适的逻辑见 {@link DefaultListableBeanFactory#determineAutowireCandidate(Map, DependencyDescriptor)}
 * <br/><br/><br/>
 * {@link Resource} 由 {@link org.springframework.context.annotation.CommonAnnotationBeanPostProcessor}处理<br/>
 * 		字段注入的入口是 {@link CommonAnnotationBeanPostProcessor#postProcessProperties(PropertyValues, Object, String)}<br/>
 * 	优先名称，如果名称在容器中不存在，就用@Autowired的处理逻辑，具体见{@link CommonAnnotationBeanPostProcessor#autowireResource(BeanFactory, CommonAnnotationBeanPostProcessor.LookupElement, String)}
 * </pre>
 */
public class AutowiredAndResourceEx {

	@Autowired
	private String myName;

	@Resource
	private String theName;

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AutowiredAndResourceEx.class);
		AutowiredAndResourceEx bean = context.getBean(AutowiredAndResourceEx.class);
		System.out.println(bean.myName);
		System.out.println(bean.theName);
	}

	@Bean
	public String yourName() {
		return "刘生";
	}
//	@Bean
//	public String myName() {
//		return "陈生";
//	}

}
