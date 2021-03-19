package main;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import spring.WriteImpl;

public class Main1 {
	public static void main(String[] args) {
		AbstractApplicationContext ctx =
						new GenericXmlApplicationContext("classpath:aop.xml");
		//bean : MariadbArticleDao 객체를 생성자를 통해서 저장하고 있는 WriteImpl 저장.
		WriteImpl bean = ctx.getBean("write",WriteImpl.class);
		bean.write();
	}
}
