package com.chenx.ioc.dependency.domain;


import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;

/**
 * User的子类
 */
@Configuration
public class Administrator extends User{
	private int permissions;

	public Long getId() {
		return super.getId();
	}

	public void setId(Long id) {
		super.setId(id);
	}

	public String getName() {
		return super.getName();
	}

	public void setName(String name) {
		super.setName(name);
	}

	public int getPermissions() {
		return permissions;
	}

	public void setPermissions(int permissions) {
		this.permissions = permissions;
	}

	@Override
	public String toString() {
		return "Administrator{" +
				"id=" + getId() +
				", name='" + getName() + '\'' +
				", Permissions='" + permissions + '\'' +
				'}';
	}
}
