package chap1;

import org.springframework.context.support.GenericXmlApplicationContext;

public class Main1 {
	public static void main(String[] args) {
		//ctx : 컨테이너. 객체들을 저장하고 있는 객체
		/*
		 * BeanFactory : 기본 컨테이너 객체. 컨테이너의 최상위 인터페이스 객체
		 * ApplicationContext : BeanFactory 인터페이스의 하위 인터페이스
		 * 				DI,AOP 기능가능. 객체생성을 미리함.
		 * WebApplicationContext : ApplicationContext 인터페이스의 하위 인터페이스
		 * 				web환경에서 사용되는 컨테이너
		 */
		GenericXmlApplicationContext ctx =
new GenericXmlApplicationContext("classpath:applicationContext.xml");
		Greeter g = ctx.getBean("greeter",Greeter.class);
		System.out.println(g.greet("스프링"));
		Message m = ctx.getBean("message",Message.class);
		m.sayHello("홍길동");
		Greeter g2 = ctx.getBean("greeter",Greeter.class);
		if(g==g2)
			System.out.println("g 객체와 g2 객체는 같은 객체임.");
	}
}
