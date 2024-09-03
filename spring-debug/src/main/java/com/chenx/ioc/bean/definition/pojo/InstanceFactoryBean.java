package com.chenx.ioc.bean.definition.pojo;

import org.springframework.beans.factory.FactoryBean;

public class InstanceFactoryBean implements FactoryBean<Instance> {
	@Override
	public Instance getObject() throws Exception {
		Instance instance = new Instance();
		instance.setOrigin("InstanceFactoryBean");
		return instance;
	}

	@Override
	public Class<?> getObjectType() {
		return Instance.class;
	}

}
