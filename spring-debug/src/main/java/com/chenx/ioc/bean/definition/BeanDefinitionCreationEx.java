package com.chenx.ioc.bean.definition;

import com.chenx.ioc.dependency.domain.User;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.GenericBeanDefinition;

/**
 * {@link  org.springframework.beans.factory.config.BeanDefinition} 构建
 */
public class BeanDefinitionCreationEx {
	public static void main(String[] args) {
		///// 1. 通过BeanDefinitionBuilder
		BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
		// 设置属性
		builder.addPropertyValue("id", 1);
		builder.addPropertyValue("name", "陈生");
		// 获取BeanDefinition
		BeanDefinition beanDefinition = builder.getBeanDefinition();
		// Generic bean: class [com.chenx.ioc.dependency.domain.User]; scope=; abstract=false; lazyInit=null; autowireMode=0; dependencyCheck=0;
		// autowireCandidate=true; primary=false; factoryBeanName=null; factoryMethodName=null; initMethodName=null; destroyMethodName=null
		System.out.println(beanDefinition);

		///// 2.通过AbstractBeanDefinition的派生类
		GenericBeanDefinition genericBeanDefinition = new GenericBeanDefinition();
		genericBeanDefinition.setBeanClass(User.class);
		// 通过MutablePropertyValues批量操作属性
		MutablePropertyValues propertyValues = new MutablePropertyValues();
		propertyValues.addPropertyValue("id", 2);
		propertyValues.addPropertyValue("name", "陈笙");
		genericBeanDefinition.setPropertyValues(propertyValues);
		// genericBeanDefinition就是一个BeanDefinition
	}
}
