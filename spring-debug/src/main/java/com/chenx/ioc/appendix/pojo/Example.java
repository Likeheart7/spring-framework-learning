package com.chenx.ioc.appendix.pojo;

import javax.annotation.PostConstruct;

public class Example {
	private String title;
	private String desc;

	@PostConstruct
	public void post(){
		System.out.println(this.title + "初始化。。。。。。");
	}

	public Example() {
	}

	public Example(String title, String desc) {
		this.title = title;
		this.desc = desc;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	@Override
	public String toString() {
		return "Example{" +
				"title='" + title + '\'' +
				", desc='" + desc + '\'' +
				'}';
	}
}
