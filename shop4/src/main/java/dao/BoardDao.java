package dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import dao.mapper.BoardMapper;
import logic.Board;


@Repository	
public class BoardDao {
	@Autowired
	private SqlSessionTemplate template;
	private Map<String,Object> param = new HashMap<>();


	public int count(String searchtype, String searchcontent) {
			param.clear();
			param.put("searchtype",searchtype);
			param.put("searchcontent",searchcontent);
		
		return template.getMapper(BoardMapper.class).count(param);
	}
	
	public List<Board> list(Integer pageNum, int limit, String searchtype, String searchcontent) {
		param.clear();
		param.put("searchtype",searchtype);
		param.put("searchcontent",searchcontent);
		param.put("startrow", (pageNum - 1) * limit);
		param.put("limit", limit);
		
		return template.getMapper(BoardMapper.class).list(param);
	}

	public Board detail(int num) {
		param.clear();
		param.put("num", num);
		return template.getMapper(BoardMapper.class).detail(param);
	}

	public void readudate(int num) {
		param.clear();
		param.put("num", num);
		template.getMapper(BoardMapper.class).readupdate(param);
	}

	public void write(Board board) {
		
		template.getMapper(BoardMapper.class).insert(board);
		
	}

	public int maxnum() {
		return template.getMapper(BoardMapper.class).maxnum();
	}



	public void updateGrpstep(Board board) {
		template.getMapper(BoardMapper.class).updateGrpstep(board);
		
	}

	public void boardupdate(Board board) {
		 template.getMapper(BoardMapper.class).boardupdate(board);
		
	}

	public void boarddelete(int num) {
		 param.clear();
		 param.put("num", num);
		 template.getMapper(BoardMapper.class).boarddelete(param);
		
	}

	public List<Map<String, Object>> graph1() {
		return template.getMapper(BoardMapper.class).graph1();
	}

	public List<Map<String, Object>> graph2() {
		return template.getMapper(BoardMapper.class).graph2();
	}


}
