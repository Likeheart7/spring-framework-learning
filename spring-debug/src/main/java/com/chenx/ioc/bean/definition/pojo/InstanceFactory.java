package com.chenx.ioc.bean.definition.pojo;

public class InstanceFactory {
	public Instance createInstance() {
		Instance instance = new Instance();
		instance.setOrigin("实例方法");
		return instance;
	}
}
