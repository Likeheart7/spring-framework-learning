package com.chenx.ioc.dependency.domain;

import org.springframework.stereotype.Component;

@Component // 加该注解用于测试
public class User {
	private Long id;
	private String name;


	public User(Long id, String name) {
		this.id = id;
		this.name = name;
	}

	public User() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", name='" + name + '\'' +
				'}';
	}
}
