package com.chenx.aop.usage;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy
@Aspect
@Configuration
public class AspectjAnnotationEx {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.register(AspectjAnnotationEx.class);
		context.refresh();
		// com.chenx.aop.usage.AspectjAnnotationEx$$EnhancerBySpringCGLIB$$cee04e8b@659499f1
		AspectjAnnotationEx bean = context.getBean(AspectjAnnotationEx.class);
		System.out.println(bean);
	}
}
