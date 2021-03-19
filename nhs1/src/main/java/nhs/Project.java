package nhs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class Project {
	private ArticleDao dao;
	@Autowired
	@Qualifier
	public void test() {
		dao.insert("OracleArticleDao insert 메서드 실행됨");
		dao.update("OracleArticleDao update 메서드 실행됨");
		dao.delete("OracleArticleDao insert 메서드 실행됨");
	}
}
