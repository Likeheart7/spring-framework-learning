package com.chenx.ioc.pojo;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class PrototypeUser {
	private Long id;
	private String name;

	public PrototypeUser() {
	}

	public PrototypeUser(Long id, String name) {
		this.id = id;
		this.name = name;
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

	@PostConstruct
	public void postConstruct() {
		System.out.println("=====>>> Prototype post construct......");
	}

	@PreDestroy
	public void preDestroy() {
		System.out.println("=====>>> Prototype pre destroy......");
	}
}
