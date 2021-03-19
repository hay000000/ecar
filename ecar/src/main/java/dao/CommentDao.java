package dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dao.mapper.BoardMapper;
import dao.mapper.CommentMapper;
import logic.Comment;

@Repository
public class CommentDao {
	@Autowired//DataSource 객체 주입.
	private SqlSessionTemplate template;
	private Map<String,Object> param = new HashMap<>();


	public int replyCount(int btype,String man,int cnt) {
		param.put("cnt", cnt);
		param.put("btype", btype);
		return template.getMapper(CommentMapper.class).count(param);
	}
	
	public List<Comment> rlist(int cnt,int btype,String man){
		param.put("cnt", cnt);
		param.put("btype", btype);
		param.put("man", man);
		return template.getMapper(CommentMapper.class).select(param);
	}
	
	
	
	
	
	
	
	
//==================== 댓글 등록 =================
	public void write( Comment comment) {
		template.getMapper(CommentMapper.class).write(comment);
		
	}

	public int maxcomnum() {
		return template.getMapper(CommentMapper.class).maxcomnum();
	}


}
