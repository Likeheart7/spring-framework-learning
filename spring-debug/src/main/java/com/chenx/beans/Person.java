package com.chenx.beans;

/**
 * 一个简单的POJO
 * 具有Setter 和 Getter，被称为Writeable 和 Readable
 */
public class Person {
	private String name;
	private Integer age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}
}
