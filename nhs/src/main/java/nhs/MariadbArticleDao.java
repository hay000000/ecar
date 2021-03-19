package nhs;

public class MariadbArticleDao implements WriteArticleService {

	@Override
	public void write(Article article) {
		ArticleDao.insert();
		
	}

	
	
}
