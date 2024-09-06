package com.chenx.aop.overview;

import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;

public class ReflectionUtilsEx {
	public static void main(String[] args) throws ClassNotFoundException {
//		通过反射获取满足条件的指定方法
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		// 注意内部类最后一个分隔符是$而不是.
		Class<?> targetClass = classLoader.loadClass("com.chenx.aop.overview.ReflectionUtilsEx$ReflectTarget");
		Method show = ReflectionUtils.findMethod(targetClass, "show", String.class);
		System.out.println("名称为 show，参数为：String的方法是：" + show);
		/*
		名称为 show，参数为：String的方法是：public abstract java.lang.String com.chenx.aop.overview.ReflectionUtilsEx$ReflectTarget.show(java.lang.String) throws java.lang.NullPointerException
		根据diWithMethods找到的方法是：public abstract java.lang.String com.chenx.aop.overview.ReflectionUtilsEx$ReflectTarget.show(java.lang.String) throws java.lang.NullPointerException
		 */
		ReflectionUtils.doWithMethods(targetClass, method ->
						System.out.println("根据diWithMethods找到的方法是：" + method)
				,
				method -> {
					Class<?>[] parameters = method.getParameterTypes();
					Class<?>[] exceptionTypes = method.getExceptionTypes();
					return parameters.length == 1
							&& parameters[0].equals(String.class)
							&& exceptionTypes.length == 1
							&& exceptionTypes[0].equals(NullPointerException.class);
				});
	}

	interface ReflectTarget {
		String show();

		String show(String msg) throws NullPointerException;
	}
}
