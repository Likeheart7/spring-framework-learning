package com.chenx.ioc.dependency.injection.repository;

import com.chenx.ioc.dependency.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.ApplicationContext;

import java.util.List;

public class UserRepository {
	private List<User> userList;	// 自定义bean

	private BeanFactory beanFactory; // 内嵌bean

	private ObjectFactory<ApplicationContext> applicationContext;	// 内嵌非bean

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

	public BeanFactory getBeanFactory() {
		return beanFactory;
	}

	public void setBeanFactory(BeanFactory beanFactory) {
		this.beanFactory = beanFactory;
	}

	public ObjectFactory<ApplicationContext> getApplicationContext() {
		return applicationContext;
	}

	public void setApplicationContext(ObjectFactory<ApplicationContext> applicationContext) {
		this.applicationContext = applicationContext;
	}

	@Override
	public String toString() {
		return "UserRepository{" +
				"userList=" + userList +
				'}';
	}
}
