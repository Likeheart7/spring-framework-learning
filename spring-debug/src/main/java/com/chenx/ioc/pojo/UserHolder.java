package com.chenx.ioc.pojo;

import com.chenx.ioc.dependency.domain.User;

/**
 * 持有{@link com.chenx.ioc.dependency.domain.User} 的Holder类
 */
public class UserHolder {
	private User user;

	public UserHolder() {
	}

	public UserHolder(User user) {
		this.user = user;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		System.out.println("=====>>> setter......");
		this.user = user;
	}

	@Override
	public String toString() {
		return "UserHolder{" +
				"user=" + user +
				'}';
	}
}
