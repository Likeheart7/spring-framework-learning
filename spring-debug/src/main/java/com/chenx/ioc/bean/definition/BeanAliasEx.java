package com.chenx.ioc.bean.definition;

import com.chenx.ioc.dependency.domain.User;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanAliasEx {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:/xml/bean-definition-context.xml");
		User user = context.getBean("user", User.class);
		User chensheng = context.getBean("chensheng", User.class);
		System.out.println(chensheng);
		System.out.println("别名bean与源bean是否为同一对象：" + (user==chensheng)); // true
	}
}
