package com.chenx.ioc.scope;

import com.chenx.ioc.pojo.PrototypeUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

/**
 * 原型模式下，Bean的生命周期不能被Spring完全管理，比如说虽然@PostConstruct会执行，但@PreDestroy不会执行
 */
public class PrototypeEx {
	@Autowired
	private PrototypeUser prototypeUser;

	@Autowired
	@Qualifier("singletonProtoTypeUser")
	private PrototypeUser singletonProtoTypeUser;

	public static void main(String[] args) {
		/*
		 * 两个PostConstruct都执行了，但是PreDestroy只执行了singleton的那个
		 * =====>>> Prototype post construct......
		 * =====>>> Prototype post construct......
		 * =====>>> Prototype pre destroy......
		 */
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(PrototypeEx.class);
		PrototypeEx bean = context.getBean(PrototypeEx.class);
		context.close();
	}


	@Bean
	@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	public PrototypeUser prototypeUser() {
		PrototypeUser prototypeUser = new PrototypeUser();
		prototypeUser.setId(10L);
		prototypeUser.setName("陈生");
		return prototypeUser;
	}

	@Bean
	public PrototypeUser singletonProtoTypeUser() {
		PrototypeUser prototypeUser = new PrototypeUser();
		prototypeUser.setId(10L);
		prototypeUser.setName("陈生");
		return prototypeUser;
	}
}
