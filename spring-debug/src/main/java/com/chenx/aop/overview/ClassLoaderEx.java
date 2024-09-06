package com.chenx.aop.overview;

public class ClassLoaderEx {
	public static void main(String[] args) {
		ClassLoader classLoader = ClassLoaderEx.class.getClassLoader();
		System.out.println(classLoader);
		while ((classLoader = classLoader.getParent()) != null) {
			System.out.println(classLoader);
		}
		// 可以看到，拿不到启动类加载器
		/*
		sun.misc.Launcher$AppClassLoader@4f2410ac
		sun.misc.Launcher$ExtClassLoader@78e03bb5
		 */
	}
}
