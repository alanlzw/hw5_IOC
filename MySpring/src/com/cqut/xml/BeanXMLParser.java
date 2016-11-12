package com.cqut.xml;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.cqut.bean.ClassPathXmlApplicationContext;
import com.cqut.bean.BeanTemplate;
import com.cqut.exception.MySpringException;


public class BeanXMLParser {
	private String beanXMLURL;	
	
	public BeanXMLParser(String beanXMLURL) {
		this.beanXMLURL = beanXMLURL;
	}
	
	public BeanTemplate getBeanTemplate(String name) throws Exception {
		BeanTemplate beanTemplate = null;
		InputStream in = BeanXMLParser.class.getResourceAsStream(beanXMLURL);
		
		if(in != null){
			SAXReader reader = new SAXReader();
			Document document = null;
			try {
				
				document = reader.read(in);
			} catch (DocumentException e) {
				e.printStackTrace();
			}
			
			
			Element rootElm = document.getRootElement();
			
			
			if(rootElm.getName().equals("beans")) {
				beanTemplate = getBeanTemplateByName(rootElm, name);
			}
			
			else {
				throw new MySpringException("xml格式错误");
			}
		}
		else {
			throw new MySpringException("未找到配置文件");
		}
		return beanTemplate;
	}
	
	private BeanTemplate getBeanTemplateByName(Element rootElm, String name) throws Exception {
		BeanTemplate beanTemplate = new BeanTemplate();
	
		List<Element> beansElements = rootElm.elements("bean");
		
		
		for(Element bean : beansElements) {
			
			if(bean.attributeValue("id").equals(name)) {
				Map<String, Object> properties = new HashMap<String, Object>();
				
				String id = bean.attributeValue("id");
				String className = bean.attributeValue("class");
				
			
				List<Element> elements = bean.elements("property");
				for(int i = 0; i < elements.size(); i++) {
					
					String propertyName = elements.get(i).attributeValue("name");
					String propertyValue = elements.get(i).getTextTrim();
					if(propertyValue.equals("")) {
						
						String href = elements.get(i).attributeValue("href");
						
						ClassPathXmlApplicationContext fac = new ClassPathXmlApplicationContext(this.beanXMLURL.substring(9));
						properties.put(propertyName, fac.getBean(href));
					}
					else {
						properties.put(propertyName, propertyValue);
					}
				}
				beanTemplate.setProperties(properties);
				beanTemplate.setClassName(className);
				beanTemplate.setId(id);
			}
		}
		return beanTemplate;
	}
	
}
