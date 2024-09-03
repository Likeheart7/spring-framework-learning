package com.chenx.ioc.bean.definition.pojo;

public class Instance {
	private String origin;


	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	@Override
	public String toString() {
		return "Instance{" +
				"origin='" + origin + '\'' +
				'}';
	}

	public static Instance createInstant() {
		Instance instance = new Instance();
		instance.setOrigin("静态方法");
		return instance;
	}
}
