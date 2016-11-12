package com.cqut.bean;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.cqut.xml.BeanXMLParser;

public class ClassPathXmlApplicationContext implements ApplicationContext{
	private BeanXMLParser beanXMLParser;
	
	public ClassPathXmlApplicationContext(String url) {
		url = "../../../" + url;	
		beanXMLParser = new BeanXMLParser(url);	
	}
	
	public Object getBean(String name) throws Exception {
		
		
		
		BeanTemplate template = beanXMLParser.getBeanTemplate(name);
		
		
		
		//使用反射来返回一个Object
		
		
		return getObjectByTemplate(template);
	}
	
	
	private Object getObjectByTemplate(BeanTemplate template) throws Exception {
		Class myClass = null;
		Object myObject = null;
		
		myClass = Class.forName(template.getClassName());	//首先根据包名+类名来产生一个Class的对象
		myObject = myClass.newInstance();	
		
		Map<String, Object> properties = template.getProperties();	
		Set<String> key = properties.keySet();	//得到的键值,用来遍历
		
		for (Iterator it = key.iterator(); it.hasNext();) {	//遍历
            String propertyName = (String) it.next();	
            Object propertyValue = properties.get(propertyName);	
            
            String methodName = "set" + propertyName.substring(0,1).toUpperCase() +
            		propertyName.substring(1);	//修改属性的方法名
            
            Field field = myClass.getDeclaredField(propertyName);	
            
            Class fieldType = field.getType();	
           
            propertyValue = translatePropertyValueByType(propertyValue, fieldType);
           
    
            Method method = myClass.getDeclaredMethod(methodName, fieldType);
         
            method.invoke(myObject, propertyValue);
        }
		return myObject;
	}

	
	private Object translatePropertyValueByType(Object propertyValue, Class fieldType) {
		if(fieldType.toString().equals("int")) {
			return Integer.parseInt(propertyValue.toString());
		}
		else if(fieldType.toString().equals("boolean")) {
			return Boolean.parseBoolean(propertyValue.toString());
		}
		else if(fieldType.toString().equals("double")) {
			return Double.parseDouble(propertyValue.toString());
		}
		else if(fieldType.toString().equals("double")) {
			return Float.parseFloat(propertyValue.toString());
		}
		else if(fieldType.toString().equals("double")) {
			return Long.parseLong(propertyValue.toString());
		}
		
		return fieldType.cast(propertyValue);
	}
}
