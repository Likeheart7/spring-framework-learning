package com.chenx;

import com.chenx.bean.DemoBean;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("com.chenx");
		DemoBean demoBean = context.getBean(DemoBean.class);
		demoBean.show();
	}
}