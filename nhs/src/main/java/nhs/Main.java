package nhs;

import org.springframework.context.support.GenericXmlApplicationContext;

public class Main {

	public static void main(String[] args) {
		GenericXmlApplicationContext ctx =  new GenericXmlApplicationContext("classpath:applicationContext.xml");

	    WriteArticleService articleService = ctx.getBean("writeArticleService",WriteArticleService.class);

	    articleService.write(new Article());

	}

}
