package nhs;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class Main2 {
	ApplicationContext ctx =    new AnnotationConfigApplicationContext(AppCtx.class);

	Project proj = ctx.getBean("project", Project.class);

	proj.test();
}
