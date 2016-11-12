package com.cqut.test;

import com.cqut.bean.ApplicationContext;
import com.cqut.bean.ClassPathXmlApplicationContext;

public class Main {
	public static void main(String[] args) throws Exception {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("config/bean.xml");
		boss boss = (boss)ctx.getBean("boss");
		System.out.println(boss.getOffice().getOfficeId());
		System.out.println("this boss has "+"the car is "+boss.getCarColor()+" with "+
	    boss.getCarId()+" and in office :"+boss.getOffice().getOfficeId());
	}
}
