package com.chenx.aop.usage;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

@Aspect
class AspectConfig {

	@Pointcut("execution(public * *(..))")
	private void anyPublicMethod(){}

	@Before("anyPublicMethod()")
	public void beforeAdvise() throws Throwable {
		System.out.println("=====>>> before advisor......");
	}
	@After("anyPublicMethod()")
	public void afterAdvise(){
		System.out.println("=====>>> after advisor......");
	}

	/**
	 * Around要通过参数JointPoint手动调用方法执行
	 * @throws Throwable
	 */
	@Around("anyPublicMethod()")
	public Object aroundAdvise(ProceedingJoinPoint joinPoint) throws Throwable {
		System.out.println("=====>>> Around advisor......");
		Object result = joinPoint.proceed();
		System.out.println("=====>>> Around advisor......");
		return result;
	}
}