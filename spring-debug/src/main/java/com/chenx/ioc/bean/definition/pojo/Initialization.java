package com.chenx.ioc.bean.definition.pojo;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class Initialization implements InitializingBean, DisposableBean {


	@PostConstruct
	public void postConstruct() {
		System.out.println("=====>>> postConstruct......");
	}
	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("=====>>> afterPropertiesSet......");
	}

	public void initMethod() {
		System.out.println("=====>>> @Bean参数指定的初始化方法......");
	}

	@Override
	public String toString() {
		return "Initialization{}";
	}

	@PreDestroy
	public void preDestroy() {
		System.out.println("=====>>> preDestroy......");
	}

	public void destroyMethod() {
		System.out.println("=====>>> @Bean参数指定的销毁方法");
	}

	@Override
	public void destroy() throws Exception {
		System.out.println("====>>> DisposableBean#destroy......");
	}
}
