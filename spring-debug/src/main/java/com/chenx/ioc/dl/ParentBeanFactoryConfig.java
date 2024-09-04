package com.chenx.ioc.dl;

import org.springframework.context.annotation.Bean;

public class ParentBeanFactoryConfig {
	@Bean
	public Object customObject () {
		return new Object();
	}
	@Bean
	public String hello () {
		return "parent Hello";
	}
}
