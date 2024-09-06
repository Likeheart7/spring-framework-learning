package com.chenx.aop.overview.interceptor;

public class RealRequest implements Request{
	private String path;

	public RealRequest(String path) {
		this.path = path;
	}

	@Override
	public void execute() {
		System.out.println("=====>>> 执行请求处理逻辑......");
	}

	public String getPath() {
		return path;
	}
}