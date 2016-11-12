package com.cqut.bean;

import java.util.Map;

//一个bean的类,于配置文件中的一个<bean>相对应
public class BeanTemplate {
	private String id;
	private String className;
	private Map<String, Object> properties;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public Map<String, Object> getProperties() {
		return properties;
	}
	public void setProperties(Map<String, Object> properties) {
		this.properties = properties;
	}
	
}
