package com.chenx.circulardepen;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClassB {
	@Autowired
	private ClassA  a;

	public ClassA getA() {
		return a;
	}

	public void setA(ClassA a) {
		this.a = a;
	}
}
