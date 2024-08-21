package com.chenx.circulardepen;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("com.chenx.circularDepen");
		ClassA bean = context.getBean(ClassA.class);
		System.out.println(bean.getB());
	}
}
